package com.xadmin.parcelsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.xadmin.parcelsystem.bean.Parcel;

public class ParcelDao {
	
private static Connection conn;

private static final String INSERT_USERS_SQL = "INSERT INTO parcel" + "  (name, nophone, noMatric, dateIn, dateOut) VALUES "
		+ " (?, ?, null, ?, 'keluar');";  

private static final String SELECT_USER_BY_ID = "select id,name,nophone,nomatric,datein,dateout from parcel where id =?";

private static final String SELECT_ALL_USERS = "select * from parcel ORDER BY datein DESC";  

private static final String DELETE_USERS_SQL = "delete from parcel where id = ?;";

private static final String UPDATE_USERS_SQL = "update parcel set name = ?,noPhone= ?, noMatric =?, dateOut=? where id = ?;";

private static final String SELECT_AVAILABLE_BY_ID = "SELECT id, name, nophone, nomatric, datein, dateout FROM parcel WHERE nomatric IS NULL ORDER BY datein DESC";

private static final String SELECT_SEARCH_BY_date_AND_NAME = "select id,name,nophone,nomatric,datein,dateout from parcel where datein like ?"
		+ "or name like ? ORDER BY datein DESC";

private static final String SELECT_SEARCH_AVAILABLE_BY_date_AND_NAME = "SELECT id, name, nophone, nomatric, datein, dateout FROM parcel WHERE noMatric is NULL "
		+ "AND (datein LIKE ? OR name LIKE ?) ORDER BY datein DESC";

private static final String SELECT_COUNT_DATEIN = "SELECT COUNT(*) AS count_datein FROM parcel WHERE nomatric IS NULL and datein like ?";

private static final String SELECT_ALL_COUNT_DATEIN = "SELECT COUNT(*) AS count_datein FROM parcel WHERE datein like ?";


public ParcelDao() {
}
    
    public static Connection getConnection() {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbname = "parcelsystem";
        String username = "root";
        String pwd = "";

        try {

            Class.forName(driver);
            conn = (Connection)DriverManager.getConnection(url + dbname, username, pwd);
            System.out.println("Connection succesful");

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
    
    public void insertUser(Parcel parcel) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, parcel.getName());
			preparedStatement.setString(2, parcel.getNoPhone());
			preparedStatement.setString(3, parcel.getDateIn()); 
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Parcel selectUser(int id) {
		Parcel parcel = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String noPhone = rs.getString("noPhone");
				String NoMatric = rs.getString("nomatric");
				String dateIn = rs.getString("dateIn");
				String dateOut = rs.getString("dateOut");
				
				parcel = new Parcel(id, name, noPhone, NoMatric,dateIn,dateOut);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return parcel;
	}

	public List<Parcel> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Parcel> parcel = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String noPhone = rs.getString("noPhone");
				String NoMatric = rs.getString("nomatric");
				String dateIn = rs.getString("dateIn");
				
				parcel.add(new Parcel(id, name, noPhone, NoMatric,dateIn));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return parcel;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Parcel parcel) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, parcel.getName());
			statement.setString(2, parcel.getNoPhone());
			statement.setString(3, parcel.getNoMatric());
			statement.setString(4, parcel.getDateOut());
			statement.setInt(5, parcel.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Parcel> selectAvailable() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Parcel> parcel = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVAILABLE_BY_ID);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String noPhone = rs.getString("noPhone");
				String NoMatric = rs.getString("nomatric");
				String dateIn = rs.getString("dateIn");
				
				parcel.add(new Parcel(id, name, noPhone, NoMatric,dateIn));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return parcel;
	}
	
	public List<Parcel> selectAllsearch(String searchTerm) {
	    List<Parcel> parcels = new ArrayList<>();
	    
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCH_BY_date_AND_NAME);) {
	        
	    	String searchValue = "%" + searchTerm + "%";
	        preparedStatement.setString(1, searchValue); // Set the search term for date
	        preparedStatement.setString(2, searchValue);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String noPhone = rs.getString("nophone");
	            String noMatric = rs.getString("nomatric");
	            String dateIn1 = rs.getString("datein");
	            
	            parcels.add(new Parcel(id, name, noPhone, noMatric, dateIn1));
	        }
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	    
	    return parcels;
	}
	
	public List<Parcel> selectAvailablesearch(String searchTerm) {
	    List<Parcel> parcels = new ArrayList<>();
	    
	    try (Connection connection = getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SEARCH_AVAILABLE_BY_date_AND_NAME);) {
	        
	    	String searchValue = "%" + searchTerm + "%";
	        preparedStatement.setString(1, searchValue); // Set the search term for date
	        preparedStatement.setString(2, searchValue);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        
	        
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String noPhone = rs.getString("nophone");
	            String noMatric = rs.getString("nomatric");
	            String dateIn1 = rs.getString("datein");
	            
	            parcels.add(new Parcel(id, name, noPhone, noMatric, dateIn1));
	        }
	    } catch (SQLException e) {
	        printSQLException(e);
	    }
	    
	    return parcels;
	}
	
	public int getCountOfDateIn(String dateIn) {
        int count = 0;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_COUNT_DATEIN)) {
        	
        	String searchValue = "%" + dateIn + "%";
            stmt.setString(1, searchValue);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("count_datein");
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return count;
    }
	
	public int getAllCountOfDateIn(String dateIn) {
        int count = 0;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_COUNT_DATEIN)) {
        	
        	String searchValue = "%" + dateIn + "%";
            stmt.setString(1, searchValue);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("count_datein");
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return count;
    }

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	

}
