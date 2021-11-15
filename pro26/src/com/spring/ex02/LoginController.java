package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("loginController")
public class LoginController {
	
	@RequestMapping(value= {"/test/loginForm.do", "/test/loginForm2.do"}, method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		
		return mav;
	}
	
	@RequestMapping(value="/test/login.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		
		mav.addObject("userID", userID);
		mav.addObject("userName", userName);
		
		return mav;
	}
	
//	@RequestMapping(value="/test/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login2(@RequestParam("userID") String userID, @RequestParam("userName") String userName,
//								HttpServletRequest request, HttpServletResponse response ) throws Exception{
//		request.setCharacterEncoding("utf-8");
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("result");
//		
//		mav.addObject("userID", userID);
//		mav.addObject("userName", userName);
//		
//		return mav;
//	}
	
	@RequestMapping(value="/test/login2.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login2(@RequestParam("userID") String userID, 
								@RequestParam(value="userName", required=true) String userName,
								@RequestParam(value="email", required=false) String email,
								HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		
		System.out.println("이메일 출력--->"+email);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		mav.addObject("userID",userID);
		mav.addObject("userName", userName);
		
		return mav;
	}
	
	@RequestMapping(value="/test/login3.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login3(@RequestParam Map<String, String> info,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		
//		String userID = info.get("userID");
//		String userName = info.get("userName");
		
		mav.addObject("info",info);
		mav.setViewName("result");
		
		return mav;
	}
	
	//@ModelAttribute를 이용해 전달되는 매개변수 값을 LoginVO 클래스와 이름이 같은 속성에 자동으로 설정한다
	//addObject()를 이용할 필요 없이 info를 이용해 바로, jsp에서 LoginVO 속성에 접근할 수 있다.
	@RequestMapping(value="/test/login4.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login4(@ModelAttribute("info") LoginVO loginVO,
							   HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		
		return mav;
	}
	
	@RequestMapping(value="/test/login5.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String login5(Model model, 
						 HttpServletRequest request,
						 HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		model.addAttribute("userID", "hong");
		model.addAttribute("userName","홍길동");
		
		return "result";
	}
}

















