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
import javax.servlet.http.HttpSession;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
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
		HttpSession session;
		String action = request.getPathInfo(); // url 확인
		
		try {
			
			if(action == null) {// url 이 없을때 
				// 페이징 값 세팅
				String _section = request.getParameter("setion");// request getParameter을 통해 jsp setion 값 가져오기. prev, next 값으로 그 페이지로 이동 
				String _pageNum = request.getParameter("pageNum");// request getParameter을 통해 jsp pageNum 값 가져오기. 현재 페이지 번호
				
				//페이징 값 int 값으로 변환
				int section = Integer.parseInt(((_section == null) ? "1" : _section)); // _section 값 확인하여 값이 존재하지 않을 시 1로 세팅
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum)); // _pageNum 값 확인하여 값이 존재하지 않을 시 1로 세팅
				
				//매개변수로 페이징 값설정
				Map<String,Integer> pagingMap = new HashMap<String,Integer>();
				pagingMap.put("section", section);  // 검색할때 페이징 처리를 위해 "section"에 section값을  세팅
				pagingMap.put("pageNum", pageNum);  // 검색할때 페이징 처리를 위해  "pageNum"에 pageNum값을  세팅

				//게시판 내용 불러오기
				Map<String,Object> map = boardService.listArticleVO(pagingMap); // 게시판 리스트와 총건수를 받음

				//jsp에 페이징값 세팅
				map.put("section", section);
				map.put("pageNum", pageNum);				
				request.setAttribute("map", map);   //request로 통해 map을 통해 값을 세팅하여 jsp에 전달
				
				//jsp 페이지 세팅
				nextPage = "/view/list.jsp";

			} else if("/list.do".equals(action)) { // url이 list.do 일때 
				// 페이징 값 세팅
				String _section = request.getParameter("setion");// request getParameter을 통해 jsp setion 값 가져오기. prev, next 값으로 그 페이지로 이동 
				String _pageNum = request.getParameter("pageNum");// request getParameter을 통해 jsp pageNum 값 가져오기. 현재 페이지 번호
				
				//페이징 값 int 값으로 변환
				int section = Integer.parseInt(((_section == null) ? "1" : _section));// _section 값 확인하여 값이 존재하지 않을 시 1로 세팅
				int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));// _pageNum 값 확인하여 값이 존재하지 않을 시 1로 세팅
				
				//매개변수로 페이징 값설정
				Map<String,Integer> pagingMap = new HashMap<String,Integer>();
				pagingMap.put("section", section);
				pagingMap.put("pageNum", pageNum);
				
				//게시판 내용 불러오기
				Map<String,Object> map = boardService.listArticleVO(pagingMap);
				
				//jsp에 페이징값 세팅
				map.put("section", section);
				map.put("pageNum", pageNum);
				request.setAttribute("map", map);
				
				//jsp 페이지 세팅
				nextPage = "/view/list.jsp";
			} else if(action.equals("/Form.do")) { // url이 Form.do 일때 
				//jsp 페이지 세팅
				nextPage = "/view/Form.jsp";
			} else if (action.equals("/add.do")){
				
				// 파일 업로드(임시폴더에 저장됨)
				Map<String, String> map = upload(request,response); // request를 매개변수로 받아 이미지를 temp 폴더에 저장하고 map에 title,imageFileName,content을 세팅한 뒤 전달받음

				
				//map에서 저장할 내용 세팅
				String imageFileName = map.get("imageFileName");
				articleVO.setTitle(map.get("title"));
				articleVO.setId("hong");
				articleVO.setParentNO(0);
				articleVO.setContent(map.get("content"));
				articleVO.setImageFileName(imageFileName);
				
				//DB에 저장
				int articleNo = boardService.add(articleVO);// vo를 받아 sql을 통해 저장.  articleNo 값을 리턴 받음

				
				//이미지 파일 임시폴더에서 옮기기
				if(imageFileName != null && imageFileName.length() !=0)
				{
					File src = new File(ARTICEL_IMAGE_REPO + "\\temp\\" + imageFileName); //파일 세팅
					File dir = new File(ARTICEL_IMAGE_REPO + "\\" + articleNo);// 폴더명 세팅
					dir.mkdir();												// articleNo를 제목으로 하는 폴더 생성
					FileUtils.moveFileToDirectory(src, dir, true); 			// src 파일을 dir 폴더로 옮김
				}
				
				//페이지 이동 및 알림창
				PrintWriter out = response.getWriter();				
				out.print("<script>" 
				+ "  alert('새글을 추가했습니다.');"   // 알림창 
				+ " location.href='" + request.getContextPath() + "/board/list.do';"  // 전체글 보기 페이지로 이동
				+ "</script>");
				
				return;
				
			} else if(action.equals("/view.do")){
				// 키값 세팅
				String no = request.getParameter("articleNO");

				// 키값을 통해 검색하여
				articleVO = boardService.view(Integer.parseInt(no));
				
				// 검색된 내용을 jsp에 세팅
				request.setAttribute("info", articleVO);
				nextPage = "/view/view.jsp";
			} else if(action.equals("/mod.do")) {
				
				// 파일 업로드(임시폴더에 저장됨)
				Map<String, String> map = upload(request,response);
				
				//저장할 내용 세팅
				String imageFileName = map.get("imageFileName");
				articleVO.setArticleNO(Integer.parseInt(map.get("articleNO")));
				articleVO.setTitle(map.get("title"));
				articleVO.setId("hong");
				articleVO.setParentNO(0);
				articleVO.setContent(map.get("content"));
				articleVO.setImageFileName(map.get("imageFileName"));

				//수정된 내용 저장
				boardService.mod(articleVO);
				
				//이미지 파일 임시폴더에서 옮기기
				if(imageFileName != null && imageFileName.length() !=0)
				{
					File src = new File(ARTICEL_IMAGE_REPO + "\\temp\\" + imageFileName);
					File dir = new File(ARTICEL_IMAGE_REPO + "\\" + articleVO.getArticleNO());
					dir.mkdir();
					FileUtils.moveFileToDirectory(src, dir, true);
				}
				
				//페이지 이동 및 알림창
				PrintWriter out = response.getWriter();
				out.print("<script>" 
				+ " alert('글을 수정했습니다.');" 
				+ " location.href='" + request.getContextPath() + "/board/view.do?articleNO=" + articleVO.getArticleNO() + "';" 
				+ "</script>");
				return;
			} else if(action.equals("/remove.do")){
				
				//키 값 확인
				String str = request.getParameter("articleNO");	//jsp에서 세팅된 articleNO값을 가져옴
				int articleNO = Integer.parseInt(str);	// articleNO값 int형으로 변환
				
				//키 값을 통해 삭제
				List<Integer> NoList = boardService.remove(articleNO); //articleNO값을 키값으로 게시판 글 삭제. 단 댓글도 삭제하고 이미지를 삭제하기 위해 리턴값으로 글과 댓글의 id값을 list로 받음

				//이미지 폴더 삭제
				for(int no : NoList){
					File dir = new File(ARTICEL_IMAGE_REPO + "\\" + no);  // 이미지 폴더 세팅
					if(dir.exists())	// 폴더 존재 확인
						FileUtils.deleteDirectory(dir); // 폴더 삭제
				}
				
				//페이지 이동 및 알림창
				PrintWriter out = response.getWriter();
				out.print("<script>" 
				+ " alert('글을 삭제했습니다.');"    //알림창 글 삭제 표출
 				+ " location.href='" + request.getContextPath() + "/board/list.do';" // 전체 글 페이지로 이동
				+ "</script>");
				return;
			} else if (action.equals("/replyForm.do")) {
				
				//세션을 통해 댓글 달릴 글 키값 저징
				String str = request.getParameter("parentNO");
			//	if(str == null)
//			/		return;
				
				int parentNO = Integer.parseInt(str);		
				session = request.getSession();
				session.setAttribute("parentNO", parentNO);
				
				//이동할 jsp 페이지 세팅 
				nextPage = "/view/reForm.jsp";
				
			} else if (action.equals("/addReply.do")) {
				session = request.getSession();
				
				//왼본글 저장
				int parentNO = (Integer) session.getAttribute("parentNO");
				//세션 삭제
				session.removeAttribute("parentNO");
				
				//파일 업로드(임시폴더에 저장됨)
				Map<String, String> articleMap = upload(request, response);
				
				//저장할 내용 세팅
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(parentNO);
				articleVO.setId("lee");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				
				// 댓글 저장
				int articleNO = boardService.add(articleVO);

				////이미지 파일 임시폴더에서 옮기기
				if (imageFileName != null && imageFileName.length() != 0) {
					File srcFile = new File(ARTICEL_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICEL_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				PrintWriter pw = response.getWriter();
				pw.print("<script>" + "  alert('답글을 추가했습니다.');" + " location.href='" + request.getContextPath()
						+ "/board/view.do?articleNO="+articleNO+"';" + "</script>");
				return;
			}

			RequestDispatcher dis = request.getRequestDispatcher(nextPage);
			dis.forward(request, response);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//리턴할 맵 생성
		Map<String, String> articleMap = new HashMap<String, String>();
		//한글 utf-8로 세팅
		String encoding = "utf-8";
		
		//폴더 세팅
		File currentDirPath = new File(ARTICEL_IMAGE_REPO);
		//팩토리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//factory의 폴더를 세팅
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
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");

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

					} 
				} 
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMap;
	}
}
