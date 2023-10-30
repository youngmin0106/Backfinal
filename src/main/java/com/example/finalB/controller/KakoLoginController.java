package com.example.finalB.controller;

import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.finalB.domain.Member;
import com.example.finalB.service.SignUpService;
import com.google.gson.Gson;
import com.example.finalB.domain.RoleType;
import com.example.finalB.domain.OAuthType;

@RestController
public class KakoLoginController {

	@Value("${kakao.default.password}")
	private String kakaoPassword;
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// 액세스토큰까지 발급받는 과정
	@GetMapping("/oauth/kakao")
	public String callBack(String code) {
		
		System.out.println("테스트");
		System.out.println("카카오에서 보낸 코드 : "  + code);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// body에 대한 데이터를 담을 컬렉션
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		
		body.add("grant_type", "authorization_code");
		body.add("client_id", "ccc3b6d2fedd138aa407aa4112b315cd");
		body.add("redirect_uri", "http://localhost:3000/oauth/kakao");
		body.add("code", code);
		
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("https://kauth.kakao.com/oauth/token",
												HttpMethod.POST,
												request,
												String.class);
		
		// 확인하기 위해 출력
		System.out.println("Kakao Token Response: " + responseEntity.getBody());
		
		// jsonData 안에 넣어둠
		String jsonData = responseEntity.getBody();
		
		// Gson 객체 생성
		Gson gson = new Gson();
		
		// JSON을 원하는 컬렉션 형태로 변환
		Map<?, ?> data = gson.fromJson(jsonData, Map.class);
		
		data.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});
		
		Member userInfo = getUserInfo((String)data.get("access_token"));
		
		Member findUser = signUpService.getMember(userInfo.getUsername());
		
		if(findUser.getUsername() == null) {
			
			signUpService.signup(userInfo);
		}
		
		// 아이디, 비번으로 token 객체 생성
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
		
		// 인증 객체 만들기
		Authentication authentication = authenticationManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "redirect:/";
	}
	
	// 사용자 정보 가져오기
	public Member getUserInfo(String accessToken) {
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("Authorization", "Bearer " + accessToken);
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(header);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
													HttpMethod.GET,
													request,
													String.class);
		
		String json = response.getBody();
		
		Gson gson = new Gson();
		
		Map<?, ?> data = gson.fromJson(json, Map.class);
		
		String username = (String)((Map<?, ?>) data.get("properties")).get("nickname");	
		String email = (String) ((Map<?, ?>) data.get("kakao_account")).get("email");
		
		Member member = new Member();
		
		member.setUsername(username);
		member.setEmail(email);
		member.setPassword(kakaoPassword);
		member.setRole(RoleType.MEMBER);
		member.setOauth(OAuthType.KAKAO); // 카카오 로그인 사용자
		
		signUpService.signup(member);
		
		return member;
		
	}
	
}














