package com.example.bracketschecker.controller;

import com.example.bracketschecker.dto.RequestDto;
import com.example.bracketschecker.dto.ResponseDto;
import com.example.bracketschecker.service.BracketsCheckerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BracketsController {

    private final BracketsCheckerService bracketsCheckerService;

    @PostMapping(path = "/checkBrackets")
    @ApiOperation("Checks brackets placement in a text. Always returns 200 OK.")
    public ResponseDto checkBrackets(@RequestBody RequestDto requestDto){
        return bracketsCheckerService.checkBrackets(requestDto);
    }
}
