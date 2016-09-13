package com.epam.mentoring.module9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public class App
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{

		Connection connection = getConnection();
		Statement statement = connection.createStatement();

		createUsersTable(statement);
		createUserIDSequence(statement);
		fillUsersTable(connection);

		List<Integer> userIDList = getUserIDsFromDB(statement);

		createFriendshipsTable(statement);
		fillFriendshipsTable(connection, userIDList);

		createPostsTable(statement);
		fillPostsTable(connection, userIDList);

		createLikesTable(statement);
		fillLikesTable(connection, userIDList);

		selectUsersWithFriendsAndLikes(connection);
	}

	private static void selectUsersWithFriendsAndLikes(Connection connection) throws SQLException
	{
		String select = "SELECT u.name, u.surname, count(l.userid) "
				+ "from USERS u "
				+ "inner join("
				+ "select userid from("
				+ "select f1.userid1 userid from FRIENDSHIPS f1 "
				+ "union all "
				+ "select f2.userid2 userid from FRIENDSHIPS f2"
				+ ") "
				+ "group by userid having count(userid) > 100) fri "
				+ "on fri.userid = u.id "
				+ "inner join LIKES l on l.userid = u.id "
				+ "where l.TIMESTAMP > ? and l.TIMESTAMP < ? "
				+ "group by l.userid, u.name, u.surname "
				+ "having count(l.userid) > ?";
		PreparedStatement ps = connection.prepareStatement(select);

		Calendar c = Calendar.getInstance();
		c.set(2015, 2, 1, 0, 0);
		Timestamp start = new Timestamp(c.getTime().getTime());
		ps.setTimestamp(1, start);

		c = Calendar.getInstance();
		c.set(2015, 3, 1, 0, 0);
		Timestamp end = new Timestamp(c.getTime().getTime());
		ps.setTimestamp(2, end);

		// use 30 as there is no 100 for any person
		ps.setInt(3, 30);

		ResultSet resultSet = ps.executeQuery();
		while (resultSet.next())
		{
			System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
		}
	}

	private static void fillLikesTable(Connection connection, List<Integer> userIDList) throws SQLException
	{
		String insertLike = "INSERT INTO Likes(postid, userid, timestamp)"
				+ "values(?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(insertLike);
		for(int i = 0; i< 400000; i++)
		{
			Random rnd = new Random();
			int userRandomIndex = rnd.nextInt(userIDList.size());
			Integer userID = userIDList.get(userRandomIndex);

			Random randomDay = new Random();
			int Low = 1;
			int High = 25;
			int day = randomDay.nextInt(High-Low) + Low;

			Random randomMonth = new Random();
			int minMonth = 0;
			int maxMonth = 11;
			int month = randomMonth.nextInt(maxMonth - minMonth) + minMonth;

			Calendar c = Calendar.getInstance();
			c.set(2015, month, day, 0, 0);
			ps.setInt(1, 1);
			ps.setInt(2, userID);
			ps.setTimestamp(3, new Timestamp(c.getTime().getTime()));
			ps.addBatch();
		}
		ps.executeBatch();
	}

	private static void fillPostsTable(Connection connection, List<Integer> userIDList) throws SQLException
	{
		String insertPost = "INSERT INTO Posts(id, userid, text, timestamp)"
				+ "values(?, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(insertPost);
		ps.setInt(1, 1);
		ps.setInt(2, userIDList.get(0));
		ps.setString(3, "Lorem Ipsum");
		ps.setTimestamp(4, new Timestamp((new Date()).getTime()));

		ps.execute();
	}

	private static void fillFriendshipsTable(Connection connection, List<Integer> userIDList) throws SQLException
	{
		String insertFriendship = "INSERT INTO FRIENDSHIPS(userid1, userid2, timestamp)"
				+ "values(?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(insertFriendship);
		for(int i = 0; i < 80; i++)
		{
			for(Integer userid2 : userIDList)
			{
				Integer userid1 = userIDList.get(i);
				if(!userid1.equals(userid2))
				{
					ps.setInt(1, userid1);
					ps.setInt(2, userid2);
					ps.setTimestamp(3, new Timestamp((new Date()).getTime()));
					ps.addBatch();
				}
			}
		}
		ps.executeBatch();
	}

	private static List<Integer> getUserIDsFromDB(Statement statement) throws SQLException
	{
		ResultSet rs = statement.executeQuery("SELECT ID FROM USERS");
		List<Integer> userIDList = new ArrayList<Integer>();
		while (rs.next())
		{
			userIDList.add(rs.getInt(1));
		}
		return userIDList;
	}

	private static int[] fillUsersTable(Connection connection) throws SQLException
	{
		String insertUser = "INSERT INTO USERS(id, name, surname, birthDate)"
				+ "values(userID_seq.nextVal, ?, ?, ?)";

		PreparedStatement ps = connection.prepareStatement(insertUser);

		for(String name : UserNames.nameList)
		{
			for(String surname : UserNames.surnameList)
			{
				ps.setString(1, name);
				ps.setString(2, surname);
				ps.setTimestamp(3, new Timestamp((new Date()).getTime()));
				ps.addBatch();
			}
		}

		return ps.executeBatch();
	}

	private static boolean createUserIDSequence(Statement statement) throws SQLException
	{
		String userIDSequence = "CREATE SEQUENCE userID_seq START WITH 1 INCREMENT BY 1 NOMAXVALUE";

		return statement.execute(userIDSequence);
	}

	private static void createLikesTable(Statement statement) throws SQLException
	{
		String createLikesTable = "CREATE TABLE Likes("
				+ "postID INTEGER,"
				+ "userID INTEGER,"
				+ "timestamp TIMESTAMP)";
		statement.execute(createLikesTable);
	}

	private static void createPostsTable(Statement statement) throws SQLException
	{
		String createPostsTable = "CREATE TABLE Posts " +
				"(id INTEGER NOT NULL, "
				+ "userID INTEGER, "
				+ "text VARCHAR(255), "
				+ "timestamp TIMESTAMP )";
		statement.execute(createPostsTable);
	}

	private static boolean createFriendshipsTable(Statement statement) throws SQLException
	{
		String createFriendshipsTable = "CREATE TABLE Friendships " +
				"(userid1 INTEGER, " +
				" userid2 INTEGER, " +
				" timestamp TIMESTAMP)";

		return statement.execute(createFriendshipsTable);
	}

	private static boolean createUsersTable(Statement statement) throws SQLException
	{
		String createUsersTable = "CREATE TABLE Users " +
				"(id INTEGER NOT NULL, " +
				" name VARCHAR(255), " +
				" surname VARCHAR(255), " +
				" birthDate TIMESTAMP, " +
				" PRIMARY KEY ( id ))";

		return statement.execute(createUsersTable);
	}

	private static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mentoring", "password");
	}
}
