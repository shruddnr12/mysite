package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.dao.UserDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.UserVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		


		String boardno = request.getParameter("no");

		new BoardDao().updateHit(Long.parseLong(boardno));
		BoardVo bo = new BoardDao().getView(Long.parseLong(boardno));

		request.setAttribute( "boardVo", bo );
		WebUtils.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
