package com.shubham.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	Connection con = null;

	public AlienRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
		String username = "root";
		String password = "password";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<Alien>();
		String sql = "select * from alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

				aliens.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aliens;
	}

	public Alien getAlien(int id) {
		String sql = "select * from alien where id=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Alien createAlien(Alien a) {
		String sql = "insert into alien values(?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, a.getId());
			st.setString(2, a.getName());
			st.setInt(3, a.getPoints());
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public Alien updateAlien(Alien a) {
		String sql = "update alien set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, a.getName());
			st.setInt(2, a.getPoints());
			st.setInt(3, a.getId());

			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	public void deleteAlien(int id) {
		String sql = "delete from alien where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
