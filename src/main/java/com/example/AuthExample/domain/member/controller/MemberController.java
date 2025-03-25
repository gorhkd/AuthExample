package com.example.AuthExample.domain.member.controller;

import com.example.AuthExample.domain.member.dto.MemberSignupDto;
import com.example.AuthExample.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberSignupDto memberSignupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
           StringBuilder errorMsg = new StringBuilder();
           bindingResult.getFieldErrors().forEach(error -> {
               errorMsg.append(error.getField())  // 오류 필드명
                       .append(": ")
                       .append(error.getDefaultMessage())  // 오류 메시지
                       .append("\n");
           });
           return ResponseEntity.badRequest().body(errorMsg.toString());
       }

        // 유효성 검사를 통과한 경우 로직 처리
        return ResponseEntity.ok("회원가입 완료!");
    }
}
