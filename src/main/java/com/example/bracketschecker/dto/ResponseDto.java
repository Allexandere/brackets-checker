package com.example.bracketschecker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    boolean isCorrect;

    public static ResponseDto createFalseResponse(){
        return new ResponseDto(false);
    }

    public static ResponseDto createTrueResponse(){
        return new ResponseDto(true);
    }
}
