package com.jx372.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jx372.mysite.dao.BoardDao;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.web.action.Action;
import com.jx372.web.util.WebUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		BoardVo bo = new BoardVo();
		bo.setTitle(title);
		bo.setContent(content);
		bo.setUserNo(Long.parseLong(no));
		
		new BoardDao().insert(bo);  // 번호	제목	글쓴이	조회수	작성일
		
		WebUtils.redirect(request.getContextPath() + "/board", request, response);
	}

}
