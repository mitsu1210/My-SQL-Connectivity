import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	
	public static String url = "jdbc:mysql://localhost/facresearchdb"; //MySQL database connection URL
    public static String user = "root";
    public static String password = "root";
 
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
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

		String paperid =request.getParameter("paperid");	//it keeps track of the paperid selected by the user to be updated
	
		PrintWriter out = response.getWriter();					//prints the value from the variable
		
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password);	//establishes mysql connection
			Statement statement = con.createStatement();
				ResultSet rs = null;
				
				//returns the records of the paperid selected by the user.
				
				String sql = "select * from papers where paperid = '" + paperid + "'";	
				rs = statement.executeQuery( sql ); // returns all records of mentioned paper id
				
				while (rs.next()) 
				{
				int pid = rs.getInt(1);
				String title = rs.getString(2);	
				String abs = rs.getString(3);
				String citation = rs.getString(4);
				out.println("<html>\n" 							
		        		 +       "<head>"
		        		 +        "<title>Faculty Research Papers</title>"
		        		 +       "<meta charset='utf-8'>"
		        		 +      "<meta name='viewport' content='width=device-width, initial-scale=1'>"
		        		 +       "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
		        		 +       "<link rel='stylesheet' href='Cssfile.css' type='text/css'/>"
		        		 +       "<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>"
		        		 +       "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" 
		        		 +     "</head>" 
		        		 +"<body style='background-color: Gray'>"
		     			+"<form action='UpdateDatabase' method='post'>"		//calls updatedatabase java servlet to update the entered value     		   
		        		 +"<div class='container'>"
		        		   +"<div class='row'>"
		        		     +"<div class='col-sm-8'>"
		        		       +"<h3></h3>"
		        		       +"<div class='well' id='holder'>"
		        		           +"<table class='table table-hover'>"
		        		          
						 +"<tr>"
		        		               +"<td><b> TITLE </b></td>"
		        		              + "<td><input type='text' name='title' style='width:300px;' value = '" + title + "' ></td>" 	
		        		           +"</tr>"
		        		           
			 +"<tr>"
			 +"<td><b> ABSTRACT </b></td>"
             + "<td><input type='text' name='abs' style='width:300px;' value = '" + abs + "' ></td>" 	
          +"</tr>"
          

			 +"<tr>"
			 +"<td><b> CITATION </b></td>"
             + "<td><input type='text' name='citation' style='width:300px;' value = '" + citation + "' ></td>" 	
          +"</tr>"
          
			+"<tr>"

			+"<input type='hidden' name='paperid' value=" +pid+ ">"
			+"<input type='hidden' name='newtitle' value=" +pid+ ">"
			+"<input type='hidden' name='paperid' value=" +pid+ ">"
			+"<input type='hidden' name='paperid' value=" +pid+ ">"
			+"	<input type='submit' name='Update' value='Update'>"	
			+ "</form>"
			+"</tr>"
	
			    		       
		        		       +"</table>"
		        		       +"</div>"
		        		     +"</div>"
		        		   +"</div>"
		        		     
		        		 +"</div>"
		        		     
		        		 +"</body>"
		        		 +"</html>"
						 );			
				}
			}
		
		
catch(Exception e)
{
	
}
		
	
	
	}

}
