import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static String url = "jdbc:mysql://localhost/facresearchdb";		//MySQL database connection URL
    public static String user = "root";
    public static String password = "root";
 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		String paperid = request.getParameter("paperid");	// Retrieves the paperid of paper to be deleted selected on the delete user page 
		String confirm = request.getParameter("confirm");   // Retrieves the confirmation selected on the delete user page
		
		if (confirm.equals("yes"))
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,user,password); //connection to db established
				Statement statement = con.createStatement();
				String authsql = "delete from authorship where paperid = '" + paperid + "'";	//deleting entries from authorship table
				statement.executeUpdate(authsql);
				String keysql = "delete from paper_keywords where paperid = '" + paperid + "'";	 //deleting entries from paper keywords table
				statement.executeUpdate(keysql);
				String sql = "delete from papers where paperid = '" + paperid + "'";	//deleting entries from papers table
				statement.executeUpdate(sql);
				response.sendRedirect("http://localhost:8080/DBConnection/DeleteMessage.html"); //Once executed sends the user to view deleted message.
			}
			catch (Exception e)	
			{
				e.printStackTrace();
			}
			
		}
		
		else
		{
			response.sendRedirect("http://localhost:8080/DBConnection/Delete.jsp"); // if user denies the deletion, shows the delete page again.
			
		}
		response.getWriter().append(paperid);
	}

}
