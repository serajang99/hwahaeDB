package pureView.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pureView.dto.LoginDto;
import pureView.exception.RecordNotFoundException;
import pureView.util.JdbcUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public void add(LoginDto m) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "INSERT INTO LOG (loginnum, logindate, logoutdate, memberid) ";
			sql += "VALUES (LOG_SEQ.NEXTVAL,SYSDATE,null,?) ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getMember_id());

			int cnt = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
	}

	@Override
	public void update_in(LoginDto m) throws SQLException, RecordNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			if (findById(m.getMember_id()) == null)
				throw new RecordNotFoundException(m.getMember_id() + "는 없는 ID입니다.");

			con = JdbcUtil.connect();

			String sql = "UPDATE LOG set logindate = SYSDATE ";
			sql += "WHERE memberid = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, m.getMember_id());

			int cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {

			throw new SQLException(e);
		} finally {

			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public void update_out(LoginDto m) throws SQLException, RecordNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			if (findById(m.getMember_id()) == null)
				throw new RecordNotFoundException(m.getMember_id() + "는 없는 ID입니다.");

			con = JdbcUtil.connect();

			String sql = "UPDATE LOG set logoutdate = SYSDATE ";
			sql += "WHERE memberid = ?";
			pstmt = con.prepareStatement(sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getMember_id());

			int cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public void delete(String m_id) throws SQLException, RecordNotFoundException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			if (findById(m_id) == null)
				throw new RecordNotFoundException(m_id + "는 없는 ID입니다.");
			con = JdbcUtil.connect();

			String sql = "DELETE LOG ";
			sql += "WHERE memberid = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, m_id);

			int count = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}

	}

	@Override
	public int count() throws SQLException {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql = "SELECT COUNT(*) FROM LOG ";
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);

		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return cnt;
	}

	@Override

	public List<LoginDto> read() throws SQLException {

		List<LoginDto> result = new ArrayList<LoginDto>();

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM LOG ORDER BY memberid";
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int login_num = rs.getInt("loginnum");
				Date login_date = rs.getDate("logindate");
				Date logout_date = rs.getDate("logoutdate");
				String member_id = rs.getString("memberid");
				LoginDto dto = new LoginDto(login_num, login_date, logout_date, member_id);
				result.add(dto);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;

	}

	@Override
	public LoginDto findById(String m_id) throws SQLException {
		LoginDto dto = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();

			String sql = "SELECT * FROM LOG where memberid = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, m_id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int login_num = rs.getInt("loginnum");
				Date login_date = rs.getDate("logindate");
				Date logout_date = rs.getDate("logoutdate");
				dto = new LoginDto(login_num, login_date, logout_date, m_id);
			}
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;

	}

}
