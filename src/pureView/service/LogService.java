package pureView.service;

import java.sql.SQLException;
import java.util.List;

import pureView.exception.DuplicatedIdException;
import pureView.exception.LogException;
import pureView.exception.RecordNotFoundException;
import pureView.dto.LoginDto;

public interface LogService {
	// ���
	public void add(LoginDto m) throws LogException;

	// ����
	public void update(LoginDto m) throws LogException, RecordNotFoundException;

	// ����
	public void delete(String id) throws LogException;

	// ����
	public int count() throws LogException;

	// ���
	public List<LoginDto> read() throws LogException;

	// �˻�
	public LoginDto findById(String id) throws LogException, RecordNotFoundException;
}
