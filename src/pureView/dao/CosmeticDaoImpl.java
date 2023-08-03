package pureView.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import pureView.dto.CosmeticDto;
import pureView.util.JdbcUtil;

public class CosmeticDaoImpl implements CosmeticDao {

	@Override
	public List<CosmeticDto> list(String cate, int ob) throws SQLException {
		List<CosmeticDto> result = new ArrayList<CosmeticDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.connect();
			String sql;
			if (cate!="전체") {
				sql = "SELECT * FROM COSMETICS WHERE CATEGORY=? ORDER BY ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cate);
				pstmt.setInt(2, ob);
			}
			else {
				sql = "SELECT * FROM COSMETICS ORDER BY COSNUM ORDER BY ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ob);
			}

			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {//조회결과가 있다
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

}