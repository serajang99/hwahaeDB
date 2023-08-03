package pureView;

import java.util.Scanner;


public class pureViewUi {
	private Scanner sc;

	public static void main(String[] args) {
		new pureViewUi().go();
	}

	private void go() {
		init();
		while (true)
			mainMenu(); // 메인메뉴 출력
	}
	
	private void mainMenu() {
		System.out.println("메인 메뉴: (0) 회원가입 (1)로그인 (2)로그아웃 "
				+ "(3)화장품 목록 (4)화장품 상세보기 "
				+ "(5)리뷰 게시판 목록 (6)리뷰 등록 (7)리뷰 상세보기(댓글 포함) (8)리뷰 수정 (9)리뷰 삭제 "
				+ "(10)댓글 등록 (11)댓글 수정 (12)댓글 삭제"
				+ "(13)종료");
		System.out.println("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		if (menu == 0) {
			
		} else if (menu == 1) {
//			login();
		} else if (menu == 2) {
//			logout();
		} else if (menu == 3) {
			
		} else if (menu == 4) {
			
		} else if (menu == 5) {
			
		} else if (menu == 6) {
			
		} else if (menu == 7) {
			
		} else if (menu == 8) {
			
		} else if (menu == 9) {
			
		} else if (menu == 10) {
			
		} else if (menu == 11) {
			
		} else if (menu == 12) {
			
		} else {
			System.exit(0);
		}
		
		
	}

	private void init() {
		sc = new Scanner(System.in);
	}
}
