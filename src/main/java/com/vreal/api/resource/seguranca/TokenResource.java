package com.vreal.api.resource.seguranca;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vreal.commom.VrealApiProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/tokens")
public class TokenResource {

	private VrealApiProperty vrealApiProperty;
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req,HttpServletResponse resp) {
		
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(false);
		cookie.setSecure(vrealApiProperty.getSeguranca().isEnableHttps());
		cookie.setPath(req.getContextPath()+"/oauth/token");
		cookie.setMaxAge(0);
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
