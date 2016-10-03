package org.meruvian.yama.webapi.interceptor;

import java.io.IOException;
import java.net.URI;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class SocialLoginInterceptor extends GenericFilterBean {
	public static final String OAUTH_AUTH_URL = "/oauth/authorize";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		
		if (!req.getServletPath().equals("/login")) {
			chain.doFilter(req, res);
			return;
		}
		
		URI uri = URI.create(getRedirectUrlAfterLogin(req));
		if (StringUtils.endsWith(uri.getPath(), OAUTH_AUTH_URL)) {
			
			// client request for social login
			for (NameValuePair param : URLEncodedUtils.parse(uri, "UTF-8")) {
				System.out.println("param: "+param.getName() +" - "+param.getValue());
				if (StringUtils.equals("social", param.getName())) {
					String redirectUrl = StringUtils.join("/login/social/", param.getValue());
					res.sendRedirect(redirectUrl);
					
					return;
				} 
				else if (StringUtils.equals("state", param.getName())) {
					System.out.println("STATE: "+param.getValue());
					HttpSession session = req.getSession(true);
					session.setAttribute(param.getName(), param.getValue());
				}
			}
		}
		
		chain.doFilter(req, res);
	}
	
	protected String getRedirectUrlAfterLogin(HttpServletRequest request) {
		HttpSession session = request.getSession(true);

		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				return savedRequest.getRedirectUrl();
			}
		}

		return "/";
	}

}