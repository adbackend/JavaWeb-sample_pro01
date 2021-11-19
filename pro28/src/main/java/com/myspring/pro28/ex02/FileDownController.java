package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownController {
	
	private static String CURR_IMAGE_REPO_PATH = "c:\\spring\\image_repo";
	
//	@RequestMapping("/download")
//	protected void download(@RequestParam("imageFileName") String imageFileName,
//							HttpServletResponse response) throws Exception{
//		
//		OutputStream out = response.getOutputStream();
//		
//		String filePath = CURR_IMAGE_REPO_PATH+"\\"+imageFileName;
//		
//		File image = new File(filePath);
//		
//		
//		int lastIndex = imageFileName.lastIndexOf("."); 
//		
//		 //확장자를 제외한 원본 이미지 파일의 이름을 가져온다
//		String fileName = imageFileName.substring(0,lastIndex);
//		
//		//원본 이미지 파일 이름과 같은 이름의 썸네일 파일에 대한 File 객체를 생성
//		File thumnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumnail"+"\\"+fileName+".png");
//		
//		//원본 이미지 파일을 가로세로가 50픽셀인 png 형식의 썸네일 이미지 파일로 생성
//		if(image.exists()) {
//			thumnail.getParentFile().mkdirs();
//			Thumbnails.of(image).size(50, 50).outputFormat("png").toFile(thumnail);
//		}
//		
//		//실제 다운로드를 하는 부분
//		//생성된 썸네일 파일을 브라우저로 전송
//		FileInputStream in = new FileInputStream(thumnail);
//		byte[] buffer = new byte[1024*8];
//		
//		while(true) {
//			int count = in.read(buffer);
//			if(count==-1) {
//				break;
//			}
//			out.write(buffer,0,count);
//		}
//		in.close();
//		out.close();
//	}
	
	
	//썸네일 이미지 바로 출력하기
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
							HttpServletResponse response) throws Exception{
		
		OutputStream out = response.getOutputStream();
		
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+imageFileName;
		
		File image = new File(filePath);
		
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumnail"+"\\"+fileName+".png");
		
		if(image.exists()) {
			Thumbnails.of(image).size(50, 50).outputFormat("png").toOutputStream(out);
		}else {
			return;
		}
		
		byte[] buffer = new byte[1024*8];
		out.write(buffer);
		out.close();
	}
	

}






