package com.xadmin.parcelsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xadmin.parcelsystem.connection.GetConnection;

public class UserDAO {

	private Connection connection;

	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean insertUser(String uname, String uemail, String umatric, String upwd) {
		boolean success = false;
		Connection con = null;

		try {
			con = GetConnection.getConnection();
			PreparedStatement pst = con.prepareStatement("insert into mpk(uname, uemail, matric, upwd) values(?, ?, ?, ?)");
			pst.setString(1, uname);
			pst.setString(2, uemail);
			pst.setString(3, umatric);
			pst.setString(4, upwd);

			int rowCount = pst.executeUpdate();

			if (rowCount > 0) {
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return success;
	}
    public boolean validateUser(String umatric, String upwd) {
        
    	boolean isValid = false;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM mpk WHERE matric = ? AND upwd = ?");
            pst.setString(1, umatric);
            pst.setString(2, upwd);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
