package pureView;

import java.util.List;
import java.util.Scanner;

import pureView.dao.BoardException;
import pureView.dto.BoardDto;
import pureView.service.BoardService;
import pureView.service.BoardServiceImpl;


public class pureViewUi {
	private Scanner sc;
	private BoardService brdSvc;
	
	public static void main(String[] args) {
		new pureViewUi().go();
	}
	
	private void init() {
		sc = new Scanner(System.in);
		brdSvc = new BoardServiceImpl();
	}
	
	private void go() {
		init();
		while (true)
			mainMenu(); // 메인메뉴 출력
	}
	
	private void mainMenu() {
		System.out.println("메인 메뉴: (0) 종료 (1)로그인 (2)로그아웃 "
				+ "(3)화장품 목록 (4)화장품 상세보기 "
				+ "(5)리뷰 게시판 목록 (6)리뷰 등록 (7)리뷰 상세보기(댓글 포함) (8)리뷰 수정 (9)리뷰 삭제 "
				+ "(10)댓글 등록 (11)댓글 수정 (12)댓글 삭제");
		System.out.println("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		if (menu == 0) {
			System.exit(0);
		} else if (menu == 1) {
//			login();
		} else if (menu == 2) {
//			logout();
		} else if (menu == 3) {
			
		} else if (menu == 4) {
			
		} else if (menu == 5) {
			listBoard();
		} else if (menu == 6) {
			addBoard();
		} else if (menu == 7) {
			
		} else if (menu == 8) {
//			updateBoard();
		} else if (menu == 9) {
//			deleteBoard();
		} else if (menu == 10) {
			
		} else if (menu == 11) {
			
		} else if (menu == 12) {
			
		} 
	}
	
	private void addBoard() {
		System.out.println("** 게시물 등록 **");
		System.out.println("제목을 입력하세요>>");
		String title = sc.nextLine();
		System.out.println("아이디를 입력하세요>>");
		String memberId = sc.nextLine();
		System.out.println("내용을 입력하세요>>");
		String BoardContent = sc.nextLine();
		System.out.println("별점을 매겨주세요 (1~5점)>>");
		int starRating = Integer.parseInt(sc.nextLine());
		System.out.println("화장품 번호를 입력해주세요>>");
		int cosNum = Integer.parseInt(sc.nextLine());
		BoardDto dto = new BoardDto(0, title, BoardContent, null, starRating, memberId, cosNum);
		try {
			brdSvc.add(dto);
		} catch (BoardException e) {
			e.printStackTrace();
			System.out.println("게시물 등록 오류");
		}
	}

	private void listBoard() {
		System.out.println("[게시물 목록]");
		List<BoardDto> list = null;
		
		try {
			list = brdSvc.list();
			for (BoardDto dto : list) {
				System.out.println(
						dto.getBoardNum()+ "      " + 
						dto.getBoardTitle() + "      " + 
						dto.getBoardContent() + "      " + 
						dto.getWriteTime() + "      " +
						dto.getStarRating() + "      " +
						dto.getMemberId() + "      " +
						dto.getCosNum());
			}
		} catch (BoardException e) {
			System.out.println("*** 서버에 오류가 발생 ***");
		}
	}
	
}
