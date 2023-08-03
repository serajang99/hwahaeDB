package pureView.dao;

import java.sql.SQLException;
import java.util.List;

import pureView.dto.CommentDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void add(CommentDto dto) throws SQLException, DuplicatedIdException {

	}

	@Override
	public void update(CommentDto dto) throws SQLException, RecordNotFoundException {

	}

	@Override
	public void delete(int no) throws SQLException, RecordNotFoundException {

	}

	@Override
	public int count() throws SQLException {
		return 0;
	}

	@Override
	public List<CommentDto> list() throws SQLException {
		return null;
	}

	@Override
	public CommentDto findById(int no) throws SQLException, RecordNotFoundException {
		return null;
	}

}
