package service;

import java.util.List;
import entity.*;
import vo.*;

public class BoardService {
	private BoardEntity be;
	
	public BoardService() {
		be = new BoardEntity();
	}
	
	public void insertBoard(BoardVO vo) throws Exception {
		be.insertArticle(vo);
	}
	public void getAllBoards(BoardVO vo) throws Exception {
		be.getArticleCount(vo);
		be.getArticles(vo);
	}
	public void getBoard(BoardVO vo) throws Exception {
		be.getArticle(vo);
	}
	public void updateBoard(BoardVO vo) throws Exception {
		be.updateGetArticle(vo);
	}
	public void updateGetBoard(BoardVO vo) throws Exception {
		be.updateArticle(vo);
	}
	public void deleteBoard(BoardVO vo) throws Exception {
		be.deleteArticle(vo);
	}
}
