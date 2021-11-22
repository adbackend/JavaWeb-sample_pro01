package com.myspring.pro29.ex03;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
	
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ArticleVO>> listArticle(){
		
		logger.info("listArticle 메서드 호출");
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		
		for(int i=0; i<10; i++) {
			
			ArticleVO vo = new ArticleVO();
			
			vo.setArticleNO(i);
			vo.setWriter("이순신"+i);
			vo.setTitle("제목"+i);
			vo.setContent("내용"+i);
			
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{articleNO}", method = RequestMethod.GET)
	public ResponseEntity<ArticleVO> findArticle(@PathVariable("articleNO") Integer articleNO){
		
		logger.info("findArticle 메서드 호출");
		
		ArticleVO vo = new ArticleVO();
		
		vo.setArticleNO(articleNO);
		vo.setWriter("홍길동");
		vo.setTitle("제목");
		vo.setContent("홍길동 글입니다.");
		
		return new ResponseEntity(vo, HttpStatus.OK);
	}
	
	//POST 방식으로 요청하므로 요청시 JSON으로 전달되는 객체를 새글로 추가한다.
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody ArticleVO articleVO){
		
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("addArticle 메서드 호출");
			logger.info(articleVO.toString());
			
			resEntity = new ResponseEntity("ADD_SUCCEEDED",HttpStatus.OK);
		}catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//수정하기
	@RequestMapping(value="/{articleNO}", method=RequestMethod.PUT)
	public ResponseEntity<String> modArticle(@PathVariable("articleNO") Integer articleNO,
											@RequestBody ArticleVO articleVO){
		
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("modArticle 메서드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity("MOD_SUCCEEDED",HttpStatus.OK);
		}catch (Exception e) {	
			resEntity = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//삭제하기
	@RequestMapping(value= "/{articleNO}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeArticle(@PathVariable("articleNO") Integer articleNO){
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("removeArticle 메서드 호출");
			logger.info(articleNO.toString());
			
			resEntity = new ResponseEntity("REMOVE_SUCCEEDED", HttpStatus.OK);
			
		}catch (Exception e) {
			resEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	
}
