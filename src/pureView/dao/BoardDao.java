package pureView.dao;

import java.sql.SQLException;
import java.util.List;

import pureView.dto.BoardDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;

public interface BoardDao {
	// 등록
	public void add(BoardDto dto) throws SQLException, DuplicatedIdException;

	// 수정
	public void update(BoardDto dto) throws SQLException, RecordNotFoundException;

	// 삭제
	public void delete(int no) throws SQLException, RecordNotFoundException;

	// 목록
	public List<BoardDto> list() throws SQLException;

	// id검색
	public BoardDto findById(int no) throws SQLException;
}
