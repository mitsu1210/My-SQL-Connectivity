import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String url = "jdbc:mysql://localhost/facresearchdb";	//MySQL database connection URL
    public static String user = "root";
    public static String password = "root";
 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
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
		//Statement statment = null;
		doGet(request, response);
	
		//declare variables to store entered data by user on front end
		
		String Title= "";
		String Author = "";
		String Abs= "";
		String Keyword = "";
		String Citation = "";
		String uid = "";
		
		
		//retrieves all the records from the HTML page to be added to the mysql database.
		
		 Title=request.getParameter("title");	
		 Abs=request.getParameter("abstract");	
		 Keyword = request.getParameter("keywords");
		 Citation =request.getParameter("content");	
		 uid = request.getParameter("uid");
				
		PrintWriter out = response.getWriter();					//prints the value from the variable
			
		try
		{
			int papid = 0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password); //connection to db established
			Statement statement = con.createStatement();
			String sql = "SELECT paperID FROM papers ORDER BY paperid DESC LIMIT 1;";  //query run to retrieve the last of the paperid value in database
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
			{
				papid = rs.getInt(1);	
			}
			
			int newpapid = papid + 1;	//increments the paperid by 1 to assign to the newly added paper. 
			
			//adds the data inserted by user on front end to the respective tables.
			
			String insertsql = "Insert into papers values ('" + newpapid + "', '" + Title + "', '" + Abs + "', '" + Citation + "')";
			statement.executeUpdate(insertsql);
			String sqlauth =  "Insert into authorship values ('" + uid + "','" + newpapid+ " ') ";
			String sqlkeyword = "Insert into paper_keywords values ('" + newpapid + "','" + Keyword+ " ') ";
			statement.executeUpdate(sqlauth);
			statement.executeUpdate(sqlkeyword);
			
			//Selects all the data of the logged in faculty member to display the newly added row
			
			sql = "select * from papers join authorship on papers.paperID = authorship.paperID where facultyid = " + uid;
			rs = statement.executeQuery(sql);
			
			//html display code
			
			out.println("<html>"
   		 +"<body style='background-color: Gray'>"
		   
   		 +"<div class='container'>"
   		   +"<div class='row'>"
   		     +"<div class='col-sm-8'>"
   		       +"<h3></h3>"
   		       +"<div class='well' id='holder'>"
   		           +"<table class='table table-hover'>"
   		          
			 +"<tr>"
   		               +"<td><b> New Article is added to the database. </b></td>"
   		               +"</tr>"
   		        +"</table>"
 		       +"</div>"
 		     +"</div>"
 		   +"</div>"
 		     
 		 +"</div>"
 		     
 		 +"</body>"
 		 +"</html>"
			
			);
			
			while (rs.next())
			{
				Title = rs.getString(2);
				Abs = rs.getString(3);
				Citation = rs.getString(4);
				
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
		     		   
		        		 +"<div class='container'>"
		        		   +"<div class='row'>"
		        		     +"<div class='col-sm-8'>"
		        		       +"<h3></h3>"
		        		       +"<div class='well' id='holder'>"
		        		           +"<table class='table table-hover'>"
		        		          
						 +"<tr>"
		        		               +"<td><b> TITLE </b></td>"
		        		               +"<td>"+ Title + "</td>"
		        		           +"</tr>"
		        		           
			 +"<tr>"
	         +"<td><b> ABSTRACT </b></td>"
	         +"<td>"+ Abs + "</td>"
	     +"</tr>"

			 +"<tr>"
	         +"<td><b> CITATION </b></td>"
	         +"<td>"+ Citation + "</td>"
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
			catch(Exception cnfe)
		{
			cnfe.printStackTrace();
		}
	}
}
	
