package org.generation.MyWebProject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.file.*;
//https://spring.io/guides/gs/securing-web/

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/aboutus").setViewName("aboutus");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/productform").setViewName("productform");
    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images")
                .addResourceLocations("classpath:/static/","classpath:/images/")
                .setCachePeriod(0);
        exposeDirectory("productImages/images", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        System.out.println(uploadPath);

        //if (dirName.startsWith("../")) dirName = dirName.replace("../","");

        System.out.println("/" +dirName + "/");
        registry.addResourceHandler("/" + dirName +"/**")
                .addResourceLocations("file:" +uploadPath+"/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }
}
