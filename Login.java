import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String url = "jdbc:mysql://localhost/facresearchdb";  //MySQL database connection URL
    public static String user = "root";
    public static String pass = "root";

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
try
{
	//retrieves all the records from the HTML page to be added to the mysql database into respective variables.
	
	String username =request.getParameter("Username");	//gets the keyword from search bar
	int usr = Integer.parseInt(username);
	String password =request.getParameter("Password");	//gets the keyword from search bar
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection(url,user,pass); //establishes mysql db connection
	Statement statement = con.createStatement();

	//session variable is declared to keep track of the userid logged in to be used on all pages till logout.
	HttpSession session = request.getSession(); 
	
	
	//Checks if user is present in the database with the correct password entered.
	String sql = "Select * from faculty where facultyID = " + usr +" and password = '" + password + "'";
	ResultSet rs = statement.executeQuery( sql );	
	
	//if the username is correct, send the user to view their profile
	if(rs.next())
	{
	
	//sends the username to the next page so that only authorized content is viewed to the user.
		
	request.setAttribute("usr", usr);	
	session.setAttribute("userId",usr);   
	
	request.getRequestDispatcher("/Profile.jsp").forward(request, response);
	
	}

	else
	{
		// if password is incorrect, redirect to invalid user page.
		response.sendRedirect("http://localhost:8080/DBConnection/InvalidUser.html");
	}
	
		
}
	catch(Exception e)
	{
		e.printStackTrace();		
	}
}

}
