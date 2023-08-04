package pureView.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import pureView.dto.CosStatistic;
import pureView.dto.CosmeticDto;
import pureView.util.JdbcUtil;

public class CosmeticDaoImpl implements CosmeticDao {

	@Override
	public List<CosmeticDto> list(String cate, String ob) throws SQLException {
		List<CosmeticDto> result = new ArrayList<CosmeticDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql;
			if (cate != "전체") {
				sql = "SELECT * FROM COSMETICS WHERE CATEGORY=? ORDER BY ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cate);
				pstmt.setString(2, ob);
			} else {
				System.out.println(cate);
				System.out.println(ob);
				sql = "SELECT * FROM COSMETICS ORDER BY ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, ob);
			}

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 조회결과가 있다
				int cosNum = rs.getInt("cosNum");
				String name = rs.getString("name");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				String company = rs.getString("company");
				int volume = rs.getInt("volume");
				CosmeticDto dto = new CosmeticDto(cosNum, name, category, price, company, volume);
				result.add(dto);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

	@Override
	public CosmeticDto findByName(String search) throws SQLException {
		CosmeticDto dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql = "SELECT * FROM COSMETICS JOIN HARMFULCOS USING(COSNUM) " + "JOIN HARMFULINGRD USING(INGRDNUM) "
					+ "WHERE NAME LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int cosNum = rs.getInt("cosNum");
				String name = rs.getString("name");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				String company = rs.getString("company");
				int volume = rs.getInt("volume");
				String ingrdName = rs.getString("ingrdName");
				String sideEffect = rs.getString("sideEffect");
				dto = new CosmeticDto(cosNum, name, category, price, company, volume, ingrdName, sideEffect);
			} else {
				System.out.println("검색 결과가 없습니다");
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return dto;
	}

	@Override
	public List<CosStatistic> statistics() throws SQLException {
		List<CosStatistic> result = new ArrayList<CosStatistic>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql;
			sql = "SELECT category, COUNT(*), ROUND(AVG(price),-3) as avg FROM COSMETICS GROUP BY category";
			pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {// 조회결과가 있다
				String category = rs.getString("category");
				int count = rs.getInt("COUNT(*)");
				int avgPrice = rs.getInt("avg");
				CosStatistic dto = new CosStatistic(category, count, avgPrice);
				result.add(dto);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new SQLException(e);
		} finally {
			JdbcUtil.close(pstmt, con);
		}
		return result;
	}

}