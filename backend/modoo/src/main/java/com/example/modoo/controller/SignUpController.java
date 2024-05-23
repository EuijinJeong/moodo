package com.example.modoo.controller;

import com.example.modoo.dto.MemberRequestDto;
import com.example.modoo.dto.MemberResponseDto;
import com.example.modoo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * SignUpController 클래스는 사용자의 회원가입 요청을 처리하는 컨트롤러입니다. 이 컨트롤러는 CORS 정책을 통해
 * 'http://localhost:3000' 도메인에서 발생하는 요청만을 수락합니다. 모든 메서드는 '/api' 경로 아래에서 작동하며,
 * 이 클래스는 생성자 주입 방식을 사용하여 AuthService의 의존성을 관리합니다.
 *
 * 주요 메서드:
 * - signUp(@RequestBody SignUpRequest signUpRequest): 이 메서드는 POST 요청을 통해 '/signup' 경로로 들어오는
 *   회원가입 요청을 처리합니다. 요청 본문에서 받은 SignUpRequest 객체를 MemberRequestDto 객체로 변환하여 AuthService에 전달하고,
 *   처리 결과를 MemberResponseDto로 반환합니다.
 *
 * 예외 처리:
 * - RuntimeException: 사용자 등록 중 발생하는 예외를 처리하며, 충돌(이미 존재하는 사용자)이 발생하면
 *   상태 코드 409(CONFLICT)와 함께 오류 메시지를 반환합니다.
 * - Exception: 기타 예외 발생 시 상태 코드 500(INTERNAL SERVER ERROR)를 반환합니다.
 *
 * 사용 방법:
 * - 사용자는 회원가입 폼에 이메일, 비밀번호, 전체 이름, 학생 ID를 입력하고, 이 데이터는 JSON 형태로 서버로 전송됩니다.
 * - 서버는 이 정보를 받아 사용자를 등록하고, 성공적으로 처리되면 사용자 정보를 담은 객체를 JSON 형태로 반환합니다.
 * - 실패한 경우에는 오류 메시지와 함께 적절한 HTTP 상태 코드가 반환됩니다.
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignUpController {

    private final AuthService authService;

    @PostMapping("/signup") // api 요청 엔드포인트
    public ResponseEntity<Object> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            // 사용자가 입력한 정보를 MemberRequestDto로 옮김
            MemberRequestDto memberRequestDto = new MemberRequestDto(
                    signUpRequest.getEmail(),
                    signUpRequest.getPassword(),
                    signUpRequest.getFullName(),
                    signUpRequest.getStudentId()
            );
            MemberResponseDto memberResponseDto = authService.signup(memberRequestDto);
            return ResponseEntity.ok(memberResponseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}