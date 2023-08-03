package pureView.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import pureView.util.JdbcUtil;
import pureView.dto.CommentDto;
import pureView.exception.RecordNotFoundException;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void add(CommentDto dto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JdbcUtil.connect();
			// 3. SQL 작성
			String sql = "INSERT INTO CMNT (num, boardNum, memberId, content, commentTime)";
			sql += "VALUES(CMNT_SEQ.nextval, ?, ?, ?, SYSDATE)";

			// 4. Statement 생성
			pstmt = conn.prepareStatement(sql);
			// 5. 데이터 설정
			pstmt.setInt(1, dto.getBoardNum());
			pstmt.setString(2, dto.getMemberId());
			pstmt.setString(3, dto.getContent());

			// 6. SQL 전송, 결과 수신
			// DML 전송: executeUpdate() : int
			// SELECT 전송: executeQuery() : ResultSet
			int count = pstmt.executeUpdate();
			System.out.println(count + "행 입력 완료");

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, conn);
		}
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
