package pureView;

import java.util.List;
import java.util.Scanner;

import pureView.exception.MemberException;
import pureView.dto.MemberDto;
import pureView.service.MemberService;
import pureView.service.MemberServiceImpl;
import pureView.exception.RecordNotFoundException;
import pureView.dto.BoardDto;
import pureView.service.BoardService;
import pureView.service.BoardServiceImpl;
import pureView.dto.CommentDto;
import pureView.dto.LoginDto;
import pureView.dto.CosmeticDto;
import pureView.exception.BoardException;
import pureView.exception.CommentException;
import pureView.exception.LogException;
import pureView.service.CommentService;
import pureView.service.CommentServiceImpl;
import pureView.service.LogService;
import pureView.service.LogServiceImpl;
import pureView.service.CosmeticService;
import pureView.service.CosmeticServiceImpl;

public class pureViewUi {
	private Scanner sc;

	private BoardService brdSvc; // 보드 서비스
	private CommentService cmtSvc; // 내용 서비스
	private MemberService mbSvc; // 회원 서비스
	private LogService logSvc; // 로그 서비스
	private CosmeticService csmtSvc; // 화장품서비스

	public static void main(String[] args) {
		new pureViewUi().go();
	}

	private void init() {
		sc = new Scanner(System.in);
		brdSvc = new BoardServiceImpl();
		cmtSvc = new CommentServiceImpl();
		csmtSvc = new CosmeticServiceImpl();
		mbSvc = new MemberServiceImpl();
		logSvc = new LogServiceImpl();
	}

	private void go() {
		init();
		while (true)
			mainMenu(); // 메인메뉴 출력
	}

	private void mainMenu() {
		System.out.println("메인 메뉴 : (0) 회원 관리 (1)로그인 (2)로그아웃 " + "(3)화장품 목록 (4)화장품 상세보기 "
				+ "(5)리뷰 게시판 목록 (6)리뷰 등록 (7)리뷰 상세보기(댓글 포함) (8)리뷰 수정 (9)리뷰 삭제 " + "(10)댓글 등록 (11)댓글 수정 (12)댓글 삭제 "
				+ "(13) 화장품 통계 (14) 종료");
		System.out.println("메뉴 선택: ");
		int menu = Integer.parseInt(sc.nextLine());
		if (menu == 0) {
			System.out.println("원하시는 기능을 선택하세요 : (0) 회원 가입 (1)회원 정보 수정 (2)회원 삭제 ");
			System.out.println("기능 선택 : ");
			int func = Integer.parseInt(sc.nextLine());
			if (func == 0) {
				addMember();
			} else if (func == 1) {
				updateMember();
			} else if (func == 2) {
				deleteMember();
			}
		} else if (menu == 1) {
			logIn();
		} else if (menu == 2) {
			logOut();
		} else if (menu == 3) {
			cosmeticsList();
		} else if (menu == 4) {
			cosmeticsDetail();
		} else if (menu == 5) {
			listBoard();
		} else if (menu == 6) {
			addBoard();
		} else if (menu == 7) {
			listDetailBoard();
		} else if (menu == 8) {
			updateBoard();
		} else if (menu == 9) {
			deleteBoard();
		} else if (menu == 10) {
			addComment();
		} else if (menu == 11) {
			updateComment();
		} else if (menu == 12) {
			deleteComment();
		} else if (menu == 13) {
			cosmeticsStatistics();
		} else {
			System.exit(0);
		}
	}


	// 회원 Member
	// (0) 회원관리 - (0) 회원가입
	private void addMember() {
		System.out.println("** 회원 가입 **");
		System.out.println("이름을 입력하세요 : ");
		String name = sc.nextLine();
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요 : ");
		String passwd = sc.nextLine();
		System.out.println("나이를 입력하세요 : ");
		int age = Integer.parseInt(sc.nextLine());
		String skin_type = "";
		while (true) {
			System.out.println("피부 타입을 입력하세요 (건성, 지성, 복합성 中 1) : ");
			skin_type = sc.nextLine();
			if ("건성".equals(skin_type) || "지성".equals(skin_type) || "복합성".equals(skin_type)) {
				break;
			}
		}
		MemberDto dto = new MemberDto(id, name, passwd, skin_type, age);
		try {
			mbSvc.add(dto);
			System.out.println("회원 가입되었습니다!");
		} catch (MemberException e) {
			System.out.println("회원 등록 오류입니다.");
		}
	}

	// (0) 회원관리 - (1) 회원정보수정
	private void updateMember() {
		System.out.println("수정할 회원 정보의 아이디를 입력하세요>> ");
		String id = (sc.nextLine());
		MemberDto dto = null;

		try {
			dto = mbSvc.findById(id);
			if (dto == null) {
				while (true) {
					System.out.println("잘못된 아이디입니다. 다시 입력해주세요 : ");
					id = sc.nextLine();
					dto = mbSvc.findById(id);
					if (dto != null) {
						break;
					}
				}
			}

			System.out.println("** 수정사항 **");
			System.out.println("수정할 이름 (없으면 엔터): ");
			String name_2 = sc.nextLine();
			name_2 = name_2.length() == 0 ? dto.getName() : name_2;
			System.out.println("수정할 비밀번호 (없으면 엔터): ");
			String passwd_2 = sc.nextLine();
			passwd_2 = passwd_2.length() == 0 ? dto.getPasswd() : passwd_2;
			System.out.println("수정할 피부타입 (건성, 지성, 복합성 中 1, 없으면 엔터) : ");
			String skin_type_2 = sc.nextLine();
			System.out.println(skin_type_2);
			if (skin_type_2.equals("")) {
				skin_type_2 = dto.getSkintype();
			}

			else if (!"건성".equals(skin_type_2) || !"지성".equals(skin_type_2) || !"복합성".equals(skin_type_2)) {

				while (true) {
					if ("건성".equals(skin_type_2) || "지성".equals(skin_type_2) || "복합성".equals(skin_type_2)) {
						break;
					} else if (skin_type_2.equals("")) {
						skin_type_2 = dto.getSkintype();
						break;
					}
					System.out.println("피부 타입을 입력하세요 (건성, 지성, 복합성 中 1, 없으면 엔터) : ");
					skin_type_2 = sc.nextLine();
				}
			}
			System.out.println("수정할 나이 (없으면 엔터) : ");
			String age_2 = sc.nextLine();
			int age_3 = 0;
			age_3 = age_2.length() == 0 ? dto.getAge() : Integer.parseInt(age_2);
			dto.setId(id);
			dto.setName(name_2);
			dto.setPasswd(passwd_2);
			dto.setSkintype(skin_type_2);
			dto.setAge(age_3);
			mbSvc.update(dto);
			System.out.println("회원 정보가 수정되었습니다.");
		} catch (NumberFormatException e) {
			System.out.println("---입력 오류---");
		} catch (MemberException e) {
			System.out.println("---회원 서버 오류---");
		}

	}

	// (0) 회원관리 - (2) 회원 삭제
	private void deleteMember() {
		System.out.println("삭제하고 싶은 회원 아이디를 입력하세요");
		String id = (sc.nextLine());

		try {
			mbSvc.delete(id);
			System.out.println(id + "가 삭제되었습니다.");
		} catch (MemberException e) {
			e.printStackTrace();
		}

		try {
			mbSvc.delete(id);
			System.out.println(id + "가 삭제되었습니다.");
		} catch (MemberException e) {
			System.out.println("---회원 서버 오류---");
		}

	}

	// 로그 Log
	// (1) 로그인
	private void logIn() {
		System.out.println("** 로그인 **");
		System.out.println("아이디를 입력하세요 : ");
		String id = sc.nextLine();

		MemberDto dto1 = null;
		try {
			dto1 = mbSvc.findById(id);
			if (dto1 == null) {
				while (true) {
					System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요 : ");
					id = sc.nextLine();
					dto1 = mbSvc.findById(id);
					if (dto1 != null) {
						break;
					}
				}
			}
			System.out.println("비밀번호를 입력하세요 : ");
			String pw = sc.nextLine();
			dto1 = mbSvc.findByPw(pw);
			if (dto1 == null) {
				while (true) {
					System.out.println("존재하지 않는 비밀번호입니다. 다시 입력해주세요 : ");
					pw = sc.nextLine();
					dto1 = mbSvc.findByPw(pw);
					if (dto1 != null) {
						break;
					}
				}
			}
		} catch (MemberException e) {
			System.out.println("---서버 오류---");
		}

		LoginDto dto2 = new LoginDto(0, null, null, id);
		LoginDto dto3 = null;

		try {
			dto3 = logSvc.findById(id);
			if (dto3 == null) {
				logSvc.add(dto2);

			} else {
				logSvc.update_in(dto2);
			}
		} catch (LogException e) {
			System.out.println("---서버 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println("---비밀번호 오류---");
		}

	}

	// (3) 로그아웃
	private void logOut() {

		System.out.println("** 로그아웃 **");
		System.out.println("아이디를 입력하세요 : ");

		String id = sc.nextLine();

		LoginDto dto = new LoginDto(0, null, null, id);

		try {
			try {
				logSvc.update_out(dto);
				System.out.println("로그아웃 되었습니다.");
			} catch (RecordNotFoundException e) {

			}
		} catch (LogException e) {
			System.out.println("로그아웃 오류");
		}
	}

	// 화장품 Cosmetics
	// (3) 화장품 목록
	private void cosmeticsList() {
		System.out.println("** 화장품 목록 **");
		List<CosmeticDto> list;

		try {
			System.out.println("보고 싶은 제품군을 번호로 작성해주세요");
			String[] category = { "스킨/토너", "세럼/앰플", "로션/크림", "전체" };
			System.out.println("(1)스킨/토너 (2)세럼/앰플 (3)로션/크림 (4)전체");
			String c = category[Integer.parseInt(sc.nextLine()) - 1];

			System.out.println("정렬 기준을 번호로 작성해주세요");
			System.out.println("(1)이름 (2)가격 (3)용량");
			String[] orderBy = { "cosnum", "price", "volume" };
			String ob = orderBy[Integer.parseInt(sc.nextLine()) - 1];

			list = csmtSvc.list(c, ob);
			for (CosmeticDto dto : list) {
				System.out.println(dto);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 입력입니다");
		}
	}

	// (4) 화장품 상세보기
	private void cosmeticsDetail() {
		System.out.println("** 화장품 상세보기 **");
		System.out.println("성분이 궁금하신 화장품 이름을 작성해주세요");
		CosmeticDto result;
		String search = sc.nextLine();
		result = csmtSvc.detail(search);
		if (result != null)
			System.out.println(result);
	}

	// 리뷰 Board
	// (5) 리뷰 게시판 목록
	private void listBoard() {
		System.out.println("** 리뷰 목록 **");
		List<BoardDto> list = null;

		try {
			list = brdSvc.list();
			for (BoardDto dto : list) {
				System.out.println(dto.getBoardNum() + "      " + dto.getBoardTitle() + "      " + dto.getBoardContent()
						+ "      " + dto.getWriteTime() + "      " + dto.getStarRating() + "      " + dto.getMemberId()
						+ "      " + dto.getCosNum());
			}
		} catch (BoardException e) {
			System.out.println("---서버 오류---");
		}

	}
	
	// (6)리뷰 등록 
	private void addBoard() {
		System.out.println("** 리뷰 등록 **");
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
			System.out.println("---게시물 등록 오류---");
		}
	}

	// (7)리뷰 상세보기
	private void listDetailBoard() {
		List<CommentDto> commentList = null;

		System.out.println("** 리뷰 상세보기 **");
		System.out.println("리뷰를 볼 게시물 번호를 입력하세요>> ");
		int BoardNum = Integer.parseInt(sc.nextLine());

		try {
			BoardDto dto = brdSvc.read(BoardNum);
			System.out.println("** 게시판 상세보기 **");
			System.out.println(dto.getBoardNum() + "      " + dto.getBoardTitle() + "      " + dto.getBoardContent()
					+ "      " + dto.getWriteTime() + "      " + dto.getStarRating() + "      " + dto.getMemberId()
					+ "      " + dto.getCosNum());
		} catch (BoardException e) {
			System.out.println("---게시판 서버 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println("없는 게시물입니다.");
		}

		try {
			commentList = cmtSvc.list(BoardNum);
			for (CommentDto dto : commentList) {
				System.out.println(dto.getNum() + "      " + dto.getContent() + "      " + dto.getCommentTime()
						+ "      " + dto.getMemberId() + "      " + dto.getBoardNum());
			}
		} catch (BoardException e) {
			System.out.println("---서버 오류---");
		}

	}
	

	// (8)리뷰 수정
	private void updateBoard() {
		System.out.println("** 리뷰 수정 **");
		System.out.println("수정할려는 게시물 번호를 입력하세요 >> ");
		int BoardNum = Integer.parseInt(sc.nextLine());
		try {
			BoardDto dto = brdSvc.read(BoardNum);
			System.out.println("** 상세보기 **");
			System.out.println("번호: " + dto.getBoardNum());
			System.out.println("현재제목: " + dto.getBoardTitle());
			System.out.println("바꾸려는 제목입력(안바꾸려면 엔터): ");
			String title = sc.nextLine();
			title = title.length() == 0 ? dto.getBoardTitle() : title;
			dto.setBoardTitle(title);
			System.out.println("작성자Id: " + dto.getMemberId());
			System.out.println("작성일: " + dto.getWriteTime());
			System.out.println("현재내용: " + dto.getBoardContent());
			System.out.println("바꾸려는 내용입력(안바꾸려면 엔터): ");
			String content = sc.nextLine();
			content = content.length() == 0 ? dto.getBoardContent() : content;
			dto.setBoardContent(content);
			brdSvc.update(dto);
			System.out.println("리뷰가 수정되었습니다.");
		} catch (BoardException e) {
			e.printStackTrace();
			System.out.println("--- 게시판 서버 오류입니다 ---");
		} catch (RecordNotFoundException e) {
			System.out.println("없는 게시물입니다");
		}
	}

	// (9)리뷰 삭제
	private void deleteBoard() {
		System.out.println("** 리뷰 삭제 **");
		System.out.println("삭제하고 싶은 게시물 번호 입력하세요");
		int no = Integer.parseInt(sc.nextLine());
		try {
			brdSvc.delete(no);
			System.out.println(no + "가 삭제되었습니다.");
		} catch (BoardException e) {
			System.out.println("---게시판 서버 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println(no + "번호 게시판이 없습니다");
		}

	}


	// Comment
	// (10) 댓글 등록
	private void addComment() {
		System.out.println("** 댓글 등록 **");
		System.out.println("게시물 번호를 입력하세요 >> ");
		int boardNum = Integer.parseInt(sc.nextLine());
		System.out.println("작성자를 입력하세요 >> ");
		String memberId = sc.nextLine();
		System.out.println("내용을 입력하세요 >> ");
		String content = sc.nextLine();
		CommentDto dto = new CommentDto(0, boardNum, memberId, content, null);
		try {
			cmtSvc.add(dto);
			System.out.println("댓글을 등록하였습니다.");
		} catch (CommentException e) {
			System.out.println("---댓글 등록 오류---");
		}
	}

	// (11) 댓글 수정
	private void updateComment() {
		System.out.println("** 댓글 수정 **");
		System.out.println("수정하고 싶은 댓글 번호를 입력하세요 >> ");
		int no = Integer.parseInt(sc.nextLine());
		System.out.println("새로운 내용을 입력하세요 >> ");
		String content = sc.nextLine();
		CommentDto dto = new CommentDto(no, 0, null, content, null);
		try {
			cmtSvc.update(dto);
			System.out.println("댓글을 수정하였습니다.");
		} catch (CommentException e) {
			e.printStackTrace();
			System.out.println("---댓글 수정 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println("댓글을 찾을 수 없습니다.");
		}
	}

	// (12) 댓글 삭제
	private void deleteComment() {
		System.out.println("** 댓글 삭제 **");
		System.out.println("삭제할 댓글 번호를 입력하세요 >> ");
		int no = Integer.parseInt(sc.nextLine());
		try {
			cmtSvc.delete(no);
			System.out.println("댓글을 삭제하였습니다.");
		} catch (CommentException e) {
			System.out.println("---댓글 삭제 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println("댓글을 찾을 수 없습니다.");
		}
	}
	
	
	// (13) 화장품 통계
	private void cosmeticsStatistics() {
		List<CosmeticDto> list;
		System.out.println("** 화장품 통계 **");
		try {
			list = csmtSvc.statistics();
			for (CosmeticDto dto : list) {
				System.out.println(dto);
			}
		} catch (CommentException e) {
			System.out.println("---댓글 삭제 오류---");
		} catch (RecordNotFoundException e) {
			System.out.println("댓글을 찾을 수 없습니다.");
		}
	}

}
