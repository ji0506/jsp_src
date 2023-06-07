package pro17.se00;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
	BoardDAO boardDAO;
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticleVO() throws SQLException{
		return boardDAO.selectAllArticle();
	}

	public int add(ArticleVO vo)
	{
		int i = boardDAO.insert(vo);
		return i;
	}

	public ArticleVO view(int parseInt) {
		// TODO Auto-generated method stub
		return boardDAO.selectArticle(parseInt);
	}

	public void mod(ArticleVO articleVO) {
		boardDAO.update(articleVO);
	}

}


