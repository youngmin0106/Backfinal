package com.example.finalB.service;

import java.security.Key;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.example.finalB.domain.RoleType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//빈으로 등록하기 위해서 @Component 어노테이션 추가
@Component
public class JwtService {

	static final long EXPIRATIONTIME = 60 * 60 * 24 * 30;

	static final String PREFIX = "Bearer";

	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public class UserClaims {
		
		private String username;
		private RoleType roleType;

		public UserClaims(String username, RoleType roleType) {
			this.username = username;
			this.roleType = roleType;
		}

		public String getUsername() {
			return username;
		}

		public RoleType getRoleType() {
			return roleType;
		}
	}

	public String getToken(String username, RoleType roleType) {
		String token = Jwts.builder().setSubject(username).claim("role", roleType.name())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).signWith(key).compact();
		
		return token;
	}

	public UserClaims getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (token != null) {
			Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.replace(PREFIX, ""))
					.getBody();

			String user = claims.getSubject();
			String roleTypeString = (String) claims.get("role");
			
			System.out.println("유저 : " + user);
			System.out.println("롤 타입 : " + roleTypeString);

			if (user != null && roleTypeString != null) {

				RoleType roleType = RoleType.valueOf(roleTypeString);
				return new UserClaims(user, roleType);
			}
		}

		return null;
	}
}
