package com.shopme.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = "user-photos";
		Path userPhotosDir = Paths.get(dirName);
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/" + dirName + "/**")
				.addResourceLocations("file:/" + userPhotosPath + "/");
		String catImgDirName = "../category-images";
		Path catImgDir = Paths.get(catImgDirName);
		String catImgDirPath = catImgDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/category-images/**")
				.addResourceLocations("file:/" + catImgDirPath + "/");
		String brandImgDirName = "../brand-logos";
		Path brandImgDir = Paths.get(brandImgDirName);
		String brandImgDirPath = brandImgDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/brand-logos/**")
				.addResourceLocations("file:/" + brandImgDirPath + "/");

	}
}
