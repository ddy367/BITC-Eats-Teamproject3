package com.bitc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.BCrypt;
import com.bitc.dto.AccountDto;
import com.bitc.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;

//	로그인 페이지, 이중 로그인 방지 추가
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userId") != null ) {
			return "redirect:/main";
		} else {
			return "/account/login";
		}
		
	}

//	회원가입 페이지, 로그인 상태에서 회원가입 불가	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userId") != null ) {
			return "redirect:/main";
		} else {
			return "/account/join";
		}
	}
	
//	마이 페이지, 비 로그인시 접속 불가
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String myPage(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null ) {
			return "/account/myPage";
		} else {
			return "redirect:/login";
		}
	
	}
	
	// 회원가입시 아이디 중복 확인
	@RequestMapping(value="/join/idOverlap", method=RequestMethod.POST)
	@ResponseBody
	public int idOverlap(AccountDto account) throws Exception {
		int overlap = accountService.idOverlapChk(account);
		return overlap;
	}
	
	// 회원가입
	@RequestMapping(value="/join/register", method=RequestMethod.POST)
	@ResponseBody
	public void registerJoin(@RequestBody AccountDto account) throws Exception {
		String password = account.getUserPw();
		account.setUserPw(BCrypt.hashpw(password, BCrypt.gensalt(10)));
		accountService.userRegister(account);
	}
	
	// 로그인 체크 성공시 세션 부여
	@RequestMapping(value="/login/loginCheck", method=RequestMethod.POST)
	@ResponseBody
	public int loginCheck(@RequestBody AccountDto account, HttpServletRequest request) throws Exception {
		
		int loginChk = accountService.loginChk(account);
		
		if(loginChk == 1) {
			
			if (BCrypt.checkpw(account.getUserPw(), accountService.selectUserInfoYn(account.getUserId()))) {
				HttpSession session = request.getSession();
				
				session.setAttribute("userId", account.getUserId());
				session.setAttribute("authority", account.getUserAuthority());
				
				// 세션정보 콘솔창에 띄우기
				/*
				String nick = (String) session.getAttribute("userId");
				String author = (String) session.getAttribute("authority");
				System.out.println("===============================");
				System.out.println("세션에 저장되어있는 이름 : " + nick);
				System.out.println("세션에 저장되어있는 권한 : " + author);
				System.out.println("===============================");
				*/
				
				// 5분간 세션유지
				session.setMaxInactiveInterval(300);
				
				//String sess = (String) session.getAttribute("userId");
				//System.out.println(sess);
				
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	
//	세션의 유무를 확인하여 없을 시 로그인 창으로 리다이렉트
	/*
	@RequestMapping(value="/loginChk", method=RequestMethod.GET)
	public String loginOk(HttpServletRequest request) throws Exception {
	
		HttpSession session = request.getSession();

		if (session.getAttribute("userId") != null ) {
			return "redirect:" + value;
		} else {
			return "redirect:/login";
		}
	}
	*/
	
	// 로그아웃
	// 로그아웃 버튼 클릭시 동작
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String Logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("authority");
		session.invalidate();
		
		return "redirect:/login";
	}
	
//	세션에 저장되어있는 아이디를 받아와 myPage에 데이터를 뿌림
	@RequestMapping(value="/myPage", method=RequestMethod.POST)
	@ResponseBody
	public AccountDto myPage(HttpServletRequest request, AccountDto account) throws Exception {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		account = accountService.userDetail(userId);
		
		return account;
	}
	
//	비밀번호를 제외한 유저정보 수정
	@RequestMapping(value="/myPage/edit", method=RequestMethod.PUT)
	@ResponseBody
	public String editUser(AccountDto account) throws Exception {
		accountService.editUser(account);
		return null;
	}
	
//	마이페이지 비밀번호 확인
	@RequestMapping(value="/myPage/pwChk", method=RequestMethod.POST)
	@ResponseBody
	public int pwChk (@RequestBody AccountDto account) throws Exception {
		
		if (BCrypt.checkpw(account.getUserPw(), accountService.selectUserInfoYn(account.getUserId()))) {
			return 1;
		} else {
			return 0;
		}
	}
	
//	마이페이지 비밀번호 수정
	@RequestMapping(value="/myPage/editPw", method=RequestMethod.PUT)
	@ResponseBody
	public void editPw(@RequestBody AccountDto account) throws Exception {
		String password = account.getUserPw();
		account.setUserPw(BCrypt.hashpw(password, BCrypt.gensalt(10)));
		accountService.editPw(account);
	}
}
