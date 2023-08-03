package pureView;

import java.util.Scanner;

import pureView.dao.MemberException;
import pureView.dto.MemberDto;
import pureView.service.MemberService;




public class pureViewUi {
	private Scanner sc;

	public static void main(String[] args) {
		new pureViewUi().go();
	}

	private MemberService mbSvc; // 회원 서비스
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
			addMember(); // 회원가입
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

	private void addMember() {
		System.out.println("** 회원 가입 **");
		System.out.println("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요 : ");
		String passwd = sc.nextLine();
		System.out.println("나이를 입력하세요 : ");
		int age = sc.nextInt();
		System.out.println("피부 타입을 입력하세요 (건성, 지성, 복합성 中 1) : ");
		String skin_type = sc.nextLine();
		
		
		// 번호(시퀀스), 제목, 작성자, 등록일, 내용 
		MemberDto dto = new MemberDto(id,name,passwd,skin_type,age);
		try {
			mbSvc.add(dto);
		} catch (MemberException e) {
			System.out.println("회원 등록 오류");
		}
	}
	

	private void init() {
		sc = new Scanner(System.in);
	}
}
