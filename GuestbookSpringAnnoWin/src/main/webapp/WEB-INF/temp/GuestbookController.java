package gntp.lesson.guestbook.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import gntp.lesson.guestbook.dao.GuestbookDAO;
import gntp.lesson.guestbook.vo.GuestbookVO;


public class GuestbookController extends MultiActionController {
	private GuestbookDAO guestbookDAO;
	
	public void setGuestbookDAO(GuestbookDAO guestbookDAO) {
		this.guestbookDAO = guestbookDAO;
	}

	public ModelAndView basic(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		ModelAndView mav =new ModelAndView();
		String viewName = this.getViewName(request);

		mav.setViewName(viewName);
		return mav;
	}
	
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		ModelAndView mav =new ModelAndView();
		//String viewName = this.getViewName(request);
		try {
			List<GuestbookVO> list = guestbookDAO.selectAll();
			mav.addObject("list",list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("listBook");
		return mav;
	}

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		// http://localhost:8090/member/listMember.do�� ��û��
		int begin = 0; //
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length(); // ��ü ��û�� �� ���̸� ����
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";"); // ��û uri�� ';'�� ���� ��� ';'���� ��ġ�� ����
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?"); // ��û uri�� '?'�� ���� ��� '?' ���� ��ġ�� ����
		} else {
			end = uri.length();
		}

		// http://localhost:8090/member/listMember.do�� ��û�� ���� '.do'�� ������
		// http://localhost:8090/member/listMember�� ���� ��,
		// �ٽ� http://localhost:8090/member/listMember���� �������� ù��° '/' ��ġ�� ����
		// ��, �� ���� listMember�� ���Ѵ�.
		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf(".")); // ��û���� �������� ���� '.'�� ��ġ�� ������,
																			// '.do' �տ������� ���ڿ��� ����
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length()); // ��û���� �������� ���� '/'��
																							// ��ġ�� ������, '/'
																							// ���������� ���ڿ��� ����
		}
		return fileName;
	}

}
