package pureView.service;

import java.sql.SQLException;

import pureView.dao.CommentDao;
import pureView.dao.CommentDaoImpl;
import pureView.dto.CommentDto;
import pureView.exception.CommentException;
import pureView.exception.RecordNotFoundException;

public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao = new CommentDaoImpl();

	@Override
	public boolean add(CommentDto dto) throws CommentException {
		try {
			commentDao.add(dto);
		} catch (SQLException e) {
			throw new CommentException(e.getMessage());
		}
		return false;
	}

	@Override
	public CommentDto read(int no) throws CommentException, RecordNotFoundException {
		return null;
	}

	@Override
	public boolean update(CommentDto dto) throws CommentException, RecordNotFoundException {
		try {
			commentDao.update(dto);
		} catch (SQLException e) {
			throw new CommentException(e.getMessage());
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(int no) throws CommentException, RecordNotFoundException {
		try {
			CommentDto dto = commentDao.findById(no);
			if(dto == null) // 해당 번호가 없음
				throw new RecordNotFoundException();
			else commentDao.delete(no);
		} catch (SQLException e) {
			throw new CommentException(e.getMessage());
		}
		return false;
	}

	@Override
	public int count() throws CommentException {
		return 0;
	}

}
