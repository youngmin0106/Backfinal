package com.example.finalB.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.finalB.domain.Member;
import com.example.finalB.service.MemberService;
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
	private MemberService memberService;
	
	
	// 액세스토큰까지 발급받는 과정
	@PostMapping("/oauth/kakao")
	   public ResponseEntity<?> kakaoLogin(@RequestBody Map<String, String> kakaoCode) {
	      
	      String code = kakaoCode.get("code");
	      

	      System.out.println("코드 " + code);
	      
	      String accessToken = memberService.getKakaoAccessToken(code);
	      System.out.println("토큰 " + accessToken);

	      Member userInfo = memberService.kakaoLogin(accessToken);

	      Member findMember = memberService.getMember(userInfo.getUsername());
	      
	      if(findMember.getUsername() == null)
	         memberService.insertMember(userInfo);
	      
	      return memberService.getResponseEntity(userInfo.getUsername(), "kagoo123");
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






