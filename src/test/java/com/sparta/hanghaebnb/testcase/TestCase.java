package com.sparta.hanghaebnb.testcase;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestCase {

    @Test
    void 날짜변환_테스트(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String s = covertString(localDateTime);
        System.out.println(s);
    }

    @Test
    void 소수_나눗셈(){
        List<Double> doubles = new ArrayList<>();


    }

    private static String covertString(LocalDateTime createdAt) {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월"));
    }
}
