package pureView;

import pureView.dto.CommentDto;
import pureView.exception.CommentException;
import pureView.exception.RecordNotFoundException;
import pureView.service.CommentService;

public class CommentServiceImpl implements CommentService {

	@Override
	public boolean add(CommentDto dto) throws CommentException {
		return false;
	}

	@Override
	public CommentDto read(int no) throws CommentException, RecordNotFoundException {
		return null;
	}

	@Override
	public boolean update(CommentDto dto) throws CommentException, RecordNotFoundException {
		return false;
	}

	@Override
	public boolean delete(int no) throws CommentException, RecordNotFoundException {
		return false;
	}

	@Override
	public int count() throws CommentException {
		return 0;
	}

}
