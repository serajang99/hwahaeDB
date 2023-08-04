package pureView.service;

import java.util.List;

import pureView.dto.CommentDto;
import pureView.exception.BoardException;
import pureView.exception.CommentException;
import pureView.exception.RecordNotFoundException;

public interface CommentService {

	// (10) 댓글 등록
	public boolean add(CommentDto dto) throws CommentException;

	// (7) 댓글 상세 보기
	public CommentDto read(int no) throws CommentException, RecordNotFoundException;

	// (11) 댓글 수정
	public boolean update(CommentDto dto) throws CommentException, RecordNotFoundException;

	// (12) 댓글 삭제
	public boolean delete(int no) throws CommentException, RecordNotFoundException;

	// 댓글 갯수
	public int count() throws CommentException;

	// 게시글 번호에 맞는 댓글 리스트
	public List<CommentDto> list(int no) throws BoardException;

}
