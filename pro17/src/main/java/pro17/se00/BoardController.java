package pro17.se00;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String ARTICEL_IMAGE_REPO = "D:\\file_repo";
	
	BoardService boardService;
	ArticleVO articleVO;
	
	
	public void init(ServletConfig config) throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String action = request.getPathInfo();
		
		try {
			List<ArticleVO> list = null;
			
			if(action == null) {
				list = boardService.listArticleVO();
				request.setAttribute("list", list);
				nextPage = "/view/list.jsp";
			} else if("/list.do".equals(action)) {
				list = boardService.listArticleVO();
				request.setAttribute("list", list);
				nextPage = "/view/list.jsp";
			} else if(action.equals("/Form.do")) {
				nextPage = "/view/Form.jsp";
				
			} else if (action.equals("/add.do")){
				Map<String, String> map = upload(request,response);
				
				String imageFileName = map.get("imageFileName");
				articleVO.setTitle(map.get("title"));
				articleVO.setId("hong");
				articleVO.setParentNO(0);
				articleVO.setContent(map.get("content"));
				articleVO.setImageFileName(map.get("imageFileName"));
				int no = boardService.add(articleVO);
				if(imageFileName != null && imageFileName.length() !=0)
				{
					File src = new File(ARTICEL_IMAGE_REPO + "\\temp\\" + imageFileName);
					File dir = new File(ARTICEL_IMAGE_REPO + "\\" + no);
					dir.mkdir();
					FileUtils.moveFileToDirectory(src, dir, true);
				}
				
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('새글을 추가했습니다.')");
				out.print("location.href='" + request.getContextPath() +"/board/list.do'");
				out.print("</script>");

				return;
				//request.setAttribute("msg","add");
				//nextPage = "/board/list.do";
				
			} else if(action.equals("/view.do")){
				String no = request.getParameter("articleNO");
				articleVO = boardService.view(Integer.parseInt(no));
				request.setAttribute("vo", articleVO);
				nextPage = "/view/view.jsp";
			} else if(action.equals("/mod.do")) {
				Map<String, String> map = upload(request,response);
				
				String imageFileName = map.get("imageFileName");

				articleVO.setArticleNO(Integer.parseInt(map.get("articleNO")));
				articleVO.setTitle(map.get("title"));
				articleVO.setId("hong");
				articleVO.setParentNO(0);
				articleVO.setContent(map.get("content"));
				articleVO.setImageFileName(map.get("imageFileName"));
				boardService.mod(articleVO);
				if(imageFileName != null && imageFileName.length() !=0)
				{
					File src = new File(ARTICEL_IMAGE_REPO + "\\temp\\" + imageFileName);
					File dir = new File(ARTICEL_IMAGE_REPO + "\\" + articleVO.getArticleNO());
					dir.mkdir();
					FileUtils.moveFileToDirectory(src, dir, true);
				}
				
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('새글을 추가했습니다.')");
				out.print("location.href='" + request.getContextPath() +"/board/list.do'");
				out.print("</script>");

				return;
				//request.setAttribute("msg","add");
				//nextPage = "/board/list.do";
			}


			RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			dis.forward(request, response);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICEL_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					//System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					//articleMap.put(fileItem.getFieldName(), fileItem.getName());
					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						String fileName = fileItem.getName().substring(idx + 1);
						System.out.println("파일명:" + fileName);
								articleMap.put(fileItem.getFieldName(), fileName);  //익스플로러에서 업로드 파일의 경로 제거 후 map에 파일명 저장);
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);

					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
}
