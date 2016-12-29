import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateDatabase
 */
@WebServlet("/UpdateDatabase")
public class UpdateDatabase extends HttpServlet {
	
	public static String url = "jdbc:mysql://localhost/facresearchdb"; //MySQL database connection URL
    public static String user = "root";
    public static String password = "root";
 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDatabase() {
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
		String paperid =request.getParameter("paperid");	//gets the keyword from search bar

		try
		{
		
		PrintWriter out = response.getWriter();					//prints the value from the variable
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,user,password);	//establishes mysql connection
		Statement statement = con.createStatement();
			
		String title = request.getParameter("title");	//gets the value of title from the user form
		String abs = request.getParameter("abs");		//gets the value of abstract from the user form
		String citation = request.getParameter("citation");	//gets the value of citation from the user form
		
		//update table query for the entered records.
		
		String sql = "Update papers set title = '" + title + "', abstract = '" + abs + "', citation = '" + citation + "' where paperid = '" + paperid + "'" ;
		statement.executeUpdate(sql);
		response.sendRedirect("http://localhost:8080/DBConnection/UpdatedMessage.html");	//once database is updated, user is shown this form.
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
