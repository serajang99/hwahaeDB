package pureView.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pureView.dto.MemberDto;
import pureView.exception.DuplicatedIdException;
import pureView.exception.RecordNotFoundException;
import pureView.util.JdbcUtil;

public class MemberDaoImpl implements MemberDao {
	@Override
	public void add(MemberDto m) throws SQLException, DuplicatedIdException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			if (findById(m.getId()) != null)
				throw new DuplicatedIdException(m.getId() + "는 이미 사용중입니다.");

			con = JdbcUtil.connect();

			String sql = "INSERT INTO MEMBER(id, name, passwd, skintype, age)";
			sql += "VALUES(?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getPasswd());
			pstmt.setString(4, m.getSkintype());
			pstmt.setInt(5, m.getAge());

			int cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public void update(MemberDto m) throws SQLException, RecordNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			if (findById(m.getId()) == null)
				throw new RecordNotFoundException(m.getId() + "는 없는 ID입니다.");

			con = JdbcUtil.connect();

			String sql = "UPDATE MEMBER set name = ?, passwd = ?, skintype = ?, age = ? ";
			sql += "WHERE id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPasswd());
			pstmt.setString(3, m.getSkintype());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getId());

			int cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			// 7. 자원 반환
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public void delete(String id) throws SQLException, RecordNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			if (findById(id) == null)
				throw new RecordNotFoundException(id + "는 없는 ID입니다.");

			con = JdbcUtil.connect();

			String sql = "DELETE MEMBER ";
			sql += "WHERE id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	public MemberDto findById(String id) throws SQLException {
		MemberDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM MEMBER where id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {// 조회결과가 있다
				String name = rs.getString("name");
				String passwd = rs.getString("passwd");
				String skin_type = rs.getString("skintype");
				int age = rs.getInt("age");
				dto = new MemberDto(id, name, passwd, skin_type, age);
//				System.out.println(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		System.out.println(dto);
		return dto;
	}

}
