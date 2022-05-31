package com.ace.hexa.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ace.hexa.dao.NewsDao;
import com.ace.hexa.dto.CategoryResponseDto;
import com.ace.hexa.dto.NewsRequestDto;
import com.ace.hexa.service.FileUploadService;

@Controller
@RequestMapping("/hexa/dashboard")
public class NewsController {

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private FileUploadService fileUploadService;

	@GetMapping("/setupCreateNews")
	public String setupCreateNews(ModelMap model) {
		ArrayList<CategoryResponseDto> news_categories = newsDao.selectAllNewsCategory();
		model.addAttribute("news_categories", news_categories);
		return "news_create";
	}

	@PostMapping("/createNews")
	public String createNews(String news_name, long news_category, String news_location, String descriptions,
			MultipartFile file, ModelMap model) throws IllegalStateException, IOException {
		NewsRequestDto dto = new NewsRequestDto();
		dto.setNews_name(news_name);
		dto.setNews_category(news_category);
		dto.setDescriptions(descriptions);
		dto.setNews_location(news_location);
		dto.setNews_img(file.getOriginalFilename());
		fileUploadService.fileUpload(file);
		newsDao.insertNews(dto);
		model.addAttribute("post_msg", "Successfully Created");
		return "news_create";
	}

}
