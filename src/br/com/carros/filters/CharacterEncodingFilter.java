package br.com.carros.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		httpResponse.setHeader("X-Content-Type-Options", "nosniff");
		httpResponse.setHeader("X-Frame-Options", "DENY");
		httpResponse.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

		chain.doFilter(request, response);
	}
}
