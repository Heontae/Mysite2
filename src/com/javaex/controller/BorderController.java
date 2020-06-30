package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;

@WebServlet("/bd")
public class BorderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		// 메인화면(리스트)
		if ("list".equals(action)) {
			BoardDao dao = new BoardDao();
			List<BoardVo> bList = dao.BoardList();
			// 리스트 응답해주기
			request.setAttribute("BoardList", bList);

			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
		}

		// 보기 화면
		else if ("read".equals(action)) {
			int num = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			dao.BoardHit(num);
			BoardVo vo = dao.BoardRead(num);
			request.setAttribute("read", vo);
			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/read.jsp");
		}

		else if ("writeForm".equals(action)) {
			int num = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			BoardVo vo = dao.BoardRead(num);
			request.setAttribute("read", vo);
		
			// 포워드
			WebUtil.forword(request, response, "/WEB-INF/views/board/writeForm.jsp");
		}
		else if ("write".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));
			
			
			BoardDao dao = new BoardDao();
			BoardVo vo = new BoardVo(title, content, no);
			dao.BoardInsert(vo);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/Mysite2/bd?action=list");
			
		}
		else if ("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			dao.BoardDelete(no);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/Mysite2/bd?action=list");
		} else if("modifyForm".equals(action)) {
			int num = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			BoardVo vo = dao.BoardRead(num);
			request.setAttribute("read", vo);
			
			WebUtil.forword(request, response, "/WEB-INF/views/board/modifyForm.jsp");
		}
		else if ("modify".equals(action)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao dao = new BoardDao();
			
			BoardVo vo = new BoardVo(title,content,no);
			
			dao.BoardUpdate(vo);
			
			
			//리다이렉트
			WebUtil.redirect(request, response, "/Mysite2/bd?action=list");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
