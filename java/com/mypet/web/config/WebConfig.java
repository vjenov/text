package com.mypet.web.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mypet.web.enums.Path;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	//설정 책 117페이지 참고
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"}; //<--web.xml에 서블릿매핑부분
	}
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		MultipartConfigElement multipartConfig = new MultipartConfigElement(Path.UPLOAD_PATH.toString(), 20971520, 41943040, 20971520);
		registration.setMultipartConfig(multipartConfig);
	}
}
