package com.myspring.pro28.ex01;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {
	
	private static final String CURR_IMAGE_REPO_PATH="C:\\spring\\image_repo";
	
	@RequestMapping(value="/form")
	public String form() {
		System.out.println("들어오긴 하니");
		return "uploadForm";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ModelAndView upload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		
		multipartRequest.setCharacterEncoding("utf-8");
		
		Map map = new HashMap(); // 매개변수와 파일 정보를 저장할 Map을 생성한다
		Enumeration enu = multipartRequest.getParameterNames(); //파라미터 id, name값을 불러 온다
		
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement(); //id
			String value = multipartRequest.getParameter(name);
			System.out.println("name값:value값--->"+name+":"+value);
			map.put(name, value);
		}
		
		List fileList = fileProcess(multipartRequest);
		map.put("fileList", fileList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("map",map);
		mav.setViewName("result");
		
		return mav;
	}
	
	private List<String> fileProcess(MultipartHttpServletRequest multipartRequest) throws Exception{
		
		List<String> fileList = new ArrayList<String>();
		Iterator<String> fileNames = multipartRequest.getFileNames(); //파일 이름이아니라-> 파라미터 이름을 불러옴(file1, file2..)
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			
			MultipartFile mfile = multipartRequest.getFile(fileName); //파일(이름, 타입, 크기)
			
			String originalFileName = mfile.getOriginalFilename(); //실제 업로드된 파일 이름을 가져온다
			
			fileList.add(originalFileName);
			
			File file = new File(CURR_IMAGE_REPO_PATH+"\\"+fileName);
			
			if(mfile.getSize()!=0) { // 디렉토리가 존재하는지 검사
				if(!file.exists()) { // 파일이 존재하지 않으면
					if(file.getParentFile().mkdirs()) { // 상위디렉토리로 빠져나가 디렉토리 생성
						file.createNewFile(); // 임시로 파일 생성
					}
				}
				mfile.transferTo(new File(CURR_IMAGE_REPO_PATH+"\\"+originalFileName)); //파일 업로드
			}
		}
		
		return fileList; // 첨부한 파일 이름이 저장된 fileList를 반환
	}
}
