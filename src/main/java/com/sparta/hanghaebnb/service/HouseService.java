package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.request.HouseRequestDto;
import com.sparta.hanghaebnb.dto.response.HouseResponseDto;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.exception.CustomException;
import com.sparta.hanghaebnb.exception.ErrorCode;
import com.sparta.hanghaebnb.repository.FacilityRepository;
import com.sparta.hanghaebnb.repository.HouseRepository;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HouseService {

    private final HouseRepository houseRepository;
    private final FacilityRepository facilityRepository;
    private final S3Uploader s3Uploader;

    /**
     * 게시글 작성 기능
     */
    public MessageResponseDto join(HouseRequestDto houseRequestDto, UserDetailsImpl userDetails) {

        String imgUrl = "";

        try{
            File resize_File = resizeImage(houseRequestDto.getFile(),250,250);
            imgUrl = s3Uploader.upload(resize_File);
        }catch (IOException | NullPointerException e){
            imgUrl = "https://cleaningproject.s3.ap-northeast-2.amazonaws.com/hanghaebnb/image_readtop_2021_125024_16126812034533410.jpeg";
        }

        // House Entity 생성
        House newHouse = House.of(houseRequestDto, userDetails.getUser(),imgUrl);

        // 편의시설 Entity 생성
        List<Facility> facilities = Arrays.stream(houseRequestDto.getFacilities()).map(f -> new Facility(f, newHouse)).collect(Collectors.toList());

        newHouse.addFacilities(facilities);

        houseRepository.save(newHouse);

        return new MessageResponseDto("성공", HttpStatus.OK);
    }


    /**
     * 게시글 전체 조회 기능 (추가 진행 예정)
     */
    @Transactional(readOnly = true)
    public List<HouseResponseDto> findAllHouse() {

        List<House> houses = houseRepository.findAllByOrderByCreatedAtDesc();

        return houses.stream()
                .map(h -> HouseResponseDto.of(h,(int)(Math.random()*100),(int)(Math.random()*100)))
                .collect(Collectors.toList());
    }

    /**
     * 해당 게시글 조회 기능(추가 진행 예정)
     */
    @Transactional(readOnly = true)
    public HouseResponseDto findHouse(Long houseId) {

        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));

        return HouseResponseDto.of(findHouse,10,10);
    }

    /**
     * 해당 게시글 수정 기능(추가 진행 예정)
     */
    public MessageResponseDto update(Long houseId, HouseRequestDto houseRequestDto) {

        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));

        facilityRepository.deleteAllByHouseId(houseId);

        List<Facility> facilities = Arrays.stream(houseRequestDto.getFacilities()).map(f -> new Facility(f, findHouse)).collect(Collectors.toList());

        findHouse.update(houseRequestDto,facilities);

        return new MessageResponseDto("수정완료",HttpStatus.OK);
    }

    /**
     * 해당 게시글 삭제 기능(추가 진행 예정)
     */
    public MessageResponseDto remove(Long houseId) {

        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_HOUSE));
        
        houseRepository.delete(findHouse);

        return new MessageResponseDto("삭제완료",HttpStatus.OK);
    }

    /**
     * 카테고리별 여행지 조회
     */
    public List<HouseResponseDto> categoryHouses(String houseCase) {

        List<House> findHouses = houseRepository.findAllByHouseCaseOrderByCreatedAtDesc(houseCase);

        return findHouses.stream().map(h -> HouseResponseDto.of(h,(int)(Math.random()*100),(int)(Math.random()*100))).collect(Collectors.toList());
    }

    /**
     * 검색 기능으로 여행지 조회
     */
    public List<HouseResponseDto> keywordHouse(String keyword) {

        List<House> findHouses = houseRepository.findAllByTitleContainsOrderByCreatedAtDesc(keyword);

        return findHouses.stream().map(h -> HouseResponseDto.of(h,(int)(Math.random()*100),(int)(Math.random()*100))).collect(Collectors.toList());
    }

    /**
     * 이미지 리사이징 기능
     */
    private File resizeImage(MultipartFile file, int targetWidth, int targetHeight) throws IOException {

        //1. MltipartFile 에서 BufferedImage로 변환
        BufferedImage originalImage = ImageIO.read(file.getInputStream());


        //2. Graphics2D 로 리사이징
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();


        //3. BufferedImage 로 File 생성
        File outputfile = new File(file.getOriginalFilename());
        try {
            if (outputfile.createNewFile()) {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                String type = file.getContentType().substring(file.getContentType().indexOf("/")+1);
                ImageIO.write(resizedImage, type, bos);

                InputStream inputStream = new ByteArrayInputStream(bos.toByteArray());

                Files.copy(inputStream, outputfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return outputfile;
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            throw new IOException();
        }
        return null;
    }
}
