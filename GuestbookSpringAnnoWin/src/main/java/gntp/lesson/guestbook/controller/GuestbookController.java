package gntp.lesson.guestbook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import gntp.lesson.guestbook.dao.GuestbookDAO;
import gntp.lesson.guestbook.vo.GuestbookVO;
import gntp.lesson.guestbook.vo.ReplyVO;

@Controller("guestbookController")
@RequestMapping("/guestbook")
public class GuestbookController {
	public GuestbookController() {
		System.out.println("Controller start");
	}
	@Autowired
	private GuestbookDAO guestbookDAO;
	
	/////////////////////    기본 메소드 형    ////////////////////////////////
	public ModelAndView basic(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String viewName = this.getViewName(request);
		
		mav.setViewName(viewName);
		return mav;
	}
	//////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/test.do", method=RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		ModelAndView mav = new ModelAndView();
		//String viewName = this.getViewName(request);
		
		mav.setViewName("welcome");
		return mav;
	}
	
	@RequestMapping(value="/list.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		ModelAndView mav = new ModelAndView();
		//String viewName = this.getViewName(request);
		try {
			ArrayList<GuestbookVO> list = guestbookDAO.selectAll();
			mav.addObject("list",list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("listBook");
		return mav;
	}
	
	@RequestMapping(value="/viewWriteBook.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewWriteBook(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		//String viewName = this.getViewName(request);
		
		mav.setViewName("writeBook");
		return mav;
	}
	
	//ModelAttribute: 전달받은 파라미터를 해당 Model의 필드값과 대조하여 일치하는 부분을 자동으로 설정하고 객체까지 생성한다
	//addObject()를 사용할 필요 없이 "info"를 넣어주는 것으로 작업 끝 
	@RequestMapping(value="/create.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView create(@ModelAttribute("info") GuestbookVO book, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		//String viewName = this.getViewName(request);
		
		//여기부터
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String userId = request.getParameter("userId");
//		GuestbookVO book = new GuestbookVO();
//		book.setTitle(title);
//		book.setContent(content);
//		book.setUserId(userId);
		//여기까지의 내용은 ModelAttribute()에서 처리한다 
		
		try {
			boolean flag = guestbookDAO.insertOne(book);
			if(flag) {
				System.out.println("새글이 등록되었습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:./list.do");
		return mav;
	}
	
	@RequestMapping(value="/delete.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView delete(@RequestParam("seq") String seq, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		// String seq= request.getParameter("seq"); -> 메서드의 파라미터에 어노테이션으로 설정
		try {
			boolean flag = guestbookDAO.deleteOne(seq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:./list.do");
		return mav;
	}
	
	@RequestMapping(value="/read.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView read(@RequestParam Map<String, String> params, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		//String viewName = this.getViewName(request);
		//String seq = request.getParameter("seq");
		String seq = params.get("seq"); // 기본적으로 request 객체를 통해 들어오는 param type은 String이다
		//String token = request.getParameter("token");
		String token = params.get("token");
		GuestbookVO book = null;
		try {
			book = guestbookDAO.selectOne(seq,token);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("book",book);
		mav.setViewName("read");
		return mav;
	}
	
	@RequestMapping(value="/writeReply.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView writeReply(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("이까지는 들어옵니다");
		System.out.println("댓글 내용은 이거다: " + request.getParameter("replyContent"));
		//@ModelAttribute("info") ReplyVO vo, 
		
		
		String seq = request.getParameter("seq");
		String content = request.getParameter("replyContent");
		ReplyVO vo = new ReplyVO();
		vo.setGbSeq(Integer.parseInt(seq));
		vo.setReplyContent(content);
		try {
			boolean flag = guestbookDAO.insertReply(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(vo.getGbSeq()+" "+vo.getReplyContent());
		//mav.setViewName("redirect:./read.do?seq="+seq);
		mav.setViewName("redirect:./read.do?seq="+vo.getGbSeq());
		return mav;
	}
	
	@RequestMapping(value="/viewUpdateBook.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewUpdateBook(@RequestParam("seq") String seq, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		//String seq = request.getParameter("seq"); -> 메서드의 파라미터에 어노테이션으로 설정
		GuestbookVO book = null;
		try {
			book = guestbookDAO.selectOneForUpdate(seq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("book",book);
		
		mav.setViewName("viewUpdateBook");
		return mav;
	}
	
	@RequestMapping(value="/update.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView update(@ModelAttribute("info") GuestbookVO book, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		//@ModelAttribute 사용 시, 파라미터의 타입이 맞지 않아도 자동으로 변환하여 생성한다 
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		//System.out.println("update "+content);
//		int readCount = Integer.parseInt(request.getParameter("readCount"));
//		GuestbookVO book = new GuestbookVO();
//		book.setTitle(title);
//		book.setContent(content);
//		book.setReadCount(readCount);
//		book.setSeq(seq);
		
		
		try {
			boolean flag = guestbookDAO.updateOne(book);
			if(flag) {
				System.out.println("글이 수정되었습니다.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.setViewName("redirect:./list.do");
		return mav;
	}
	////////////////////////////////// getView  /////////////////////////////////////
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		
		int begin = 0; //
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length(); 
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?"); 
		} else {
			end = uri.length();
		}


		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf(".")); 
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length()); 
		}
		return fileName;
	}
}
