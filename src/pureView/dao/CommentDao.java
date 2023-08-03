package pureView.dao;

import java.sql.SQLException;
import java.util.List;

import pureView.dto.CommentDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;

public interface CommentDao {
	// 등록
	public void add(CommentDto dto) throws SQLException;

	// 수정
	public void update(CommentDto dto) throws SQLException, RecordNotFoundException;

	// 삭제
	public void delete(int no) throws SQLException, RecordNotFoundException;

	// 갯수
	public int count() throws SQLException;

	// 목록
	public List<CommentDto> list() throws SQLException;

	// 검색
	public CommentDto findById(int no) throws SQLException, RecordNotFoundException;
}
