package com.ace.hexa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ace.hexa.dto.CategoryResponseDto;
import com.ace.hexa.dto.NewsRequestDto;

@Service
public class NewsDao {

	public static Connection con = null;
	static {
		try {
			con = JdbcConnection.getConnection();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList<CategoryResponseDto> selectAllNewsCategory() {
		ArrayList<CategoryResponseDto> list = new ArrayList<>();
		String sql = "select * from news_category";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryResponseDto res = new CategoryResponseDto();
				res.setNews_category_id(rs.getLong("news_category_id"));
				res.setNews_category_name(rs.getString("news_category_name"));
				list.add(res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

//	public ArrayList<CategoryResponseDto> selectAllNews() {
//		ArrayList<CategoryResponseDto> list = new ArrayList<>();
//		String sql = "select * from news";
//		try {
//			PreparedStatement ps = con.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				CategoryResponseDto res = new CategoryResponseDto();
//				res.setNews_category_id(rs.getLong("news_category_id"));
//				res.setNews_category_name(rs.getString("news_category_name"));
//				list.add(res);
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return list;
//	}

	public int insertNews(NewsRequestDto dto) {
		String sql = "insert into news (news_name, descriptions, news_img, news_location, news_category) values(?, ?, ?, ?, ?)";
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getNews_name());
			ps.setString(2, dto.getDescriptions());
			ps.setString(3, dto.getNews_img());
			ps.setString(4, dto.getNews_location());
			ps.setLong(5, dto.getNews_category());

			i = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return i;
	}

}
