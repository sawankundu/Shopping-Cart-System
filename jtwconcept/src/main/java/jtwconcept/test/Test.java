package jtwconcept.test;
import io.jsonwebtoken.Claims;
import jtwconcept.util.JwtUtil;

public class Test {

	public static void main(String[] args) {
		String key = "SAWAN";
		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken("Ananya", "ana" ,key);
		Claims c = jwtUtil.getClaims(key, token);
		System.out.println(token);

	}

}
