package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.svc.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestToken = request.getHeader("Authorization");
		
		String username= null;
		String jwtToken = null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer ")) {
			jwtToken = requestToken.substring(7);
			try {
				username = jwtUtils.extractUsername(jwtToken);
			}catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("Token expired");
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Unexpected Error");
			}
			
		}else {
			System.out.println("Invalid Token");
		}
		
		//validated
		if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails=  userDetailsServiceImpl.loadUserByUsername(username);
			if(jwtUtils.validateToken(jwtToken, userDetails)) {
				//token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("Token is not Valid");
		}
		filterChain.doFilter(request, response);
	}

}
