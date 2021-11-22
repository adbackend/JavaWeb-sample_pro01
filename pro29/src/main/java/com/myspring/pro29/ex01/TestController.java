package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller
@RequestMapping("/test/*")
public class TestController {

	static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/hello")
	public String hello() {
		
		return "Hello REST !!";
	}
	
	@RequestMapping("/member")
	public MemberVO member() {
		
		MemberVO vo = new MemberVO();
		
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		
		return vo;
	}
	
	@RequestMapping("/membersList")
	public List<MemberVO> listMembers(){
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		for(int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			
			vo.setId("hong"+i);
			vo.setPwd("1234"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong@test@test.com"+i);
			
			list.add(vo);
		}
		
		return list; //ArrayList를 JSON으로 브라우저에 전송한다
	}
	
	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap(){
		
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		
		for(int i=0; i<10; i++) {
			
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("1234"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong@test.com "+i);
			System.out.println();
			map.put(i, vo);
		}
		return map;
	}
	
	@RequestMapping(value="/notice/{num}", method=RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception{
		
		return num;
	}
	
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo) { //JSON으로 전송된 데이터를 MemberVO 객체의 속성에 자동을 설정해준다
		logger.info(vo.toString());
	}
	
	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberVO>> listMembers2(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for(int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			
			  vo.setId("lee" + i);
			  vo.setPwd("123"+i);
			  vo.setName("이순신" + i);
		      vo.setEmail("lee"+i+"@test.com");
			  list.add(vo);
		}
		
		return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/res3")
	public ResponseEntity res3() {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //전송할 데이터의 종료와 인코딩을 설정
		
		String message = "<script>";
		message += " alert('새 회원을 등록합니다.');";
		message += " location.href='/pro29/test/membersList2'; ";
		message += " </script>";
		
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	}
	
	
}






