import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String url = "jdbc:mysql://localhost/facresearchdb"; //MySQL database connection URL
    public static String user = "root";
    public static String password = "root";
 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		
		String wordsearch=request.getParameter("search");		//gets the keyword from search bar
		String radiosel = request.getParameter("search_type");
		
		PrintWriter out = response.getWriter();					//prints the value from the variable
			
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,user,password); 	//establishes mysql connection
			Statement statement = con.createStatement();
			
			// executes this code if the radio button selected is title
			
			if(radiosel.equals("title"))
			{	
				ResultSet rs = null;
				String sql = "select X.paperid, X.title, X.abstract, X.citation, faculty.email, faculty.fName, faculty.lname from (select papers.paperid, papers.title, papers.abstract, papers.citation, authorship.facultyID from papers JOIN authorship on papers.paperID = authorship.paperID where title REGEXP '" +wordsearch+ "') as X join faculty on X.facultyID = faculty.facultyID";	
				rs = statement.executeQuery( sql ); //all results received.. in our case more than one
				
				if (!rs.next() ) {
					response.sendRedirect("http://localhost:8080/DBConnection/NoResultFound.html");
				} 
						
				else
				{
					do
					{
						int paperid = rs.getInt(1);
						String title = rs.getString(2);	
						String abs = rs.getString(3);
						String citation = rs.getString(4);
						String mail = rs.getString(5);
						String fname = rs.getString(6);
						String lname = rs.getString(7);
						
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
				        		               +"<td>"+ title + "</td>"
				        		           +"</tr>"
				        		           
					 +"<tr>"
			         +"<td><b> ABSTRACT </b></td>"
			         +"<td>"+ abs + "</td>"
			     +"</tr>"

					 +"<tr>"
			         +"<td><b> CITATION </b></td>"
			         +"<td>"+ citation + "</td>"
			     +"</tr>"
			     
	 +"<tr>"
	 +"<td><b> FACULTY NAME </b></td>"
	 +"<td>"+ fname + " " + lname + "</td>"
	+"</tr>"

			     
			 +"<tr>"
			 +"<td><b> FACULTY EMAIL ADDRESS </b></td>"
			 +"<td>"+ mail + "</td>"
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
				while (rs.next()) ;
								}
				
				}
			
			// executes this code if the radio button selected is author
			
			else if (radiosel.equals("author"))
			{	
				ResultSet rs=null;
				
				String sql1 = "select papers.paperid, papers.title, papers.abstract, papers.citation, X.email, X.fName, X.lname from (select faculty.facultyid, faculty.email, faculty.fName, faculty.lname, authorship.paperid from faculty join authorship on faculty.facultyid = authorship.facultyid where faculty.fname = '" +wordsearch+ "' ) as X join papers on X.paperID = papers.paperID ";
				
				rs = statement.executeQuery( sql1 );	
			
				if (!rs.next() ) 
				{
					response.sendRedirect("http://localhost:8080/DBConnection/NoResultFound.html");
				} 
				
				else 
				{
						do 
						{
						int paperid = rs.getInt(1);
						String title = rs.getString(2);	
						String abs = rs.getString(3);
						String citation = rs.getString(4);
						String mail = rs.getString(5);		
						String fname = rs.getString(6);
						String lname = rs.getString(7);
						
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
				        		               +"<td>"+ title + "</td>"
				        		           +"</tr>"
				        		           
					 +"<tr>"
			         +"<td><b> ABSTRACT </b></td>"
			         +"<td>"+ abs + "</td>"
			     +"</tr>"

					 +"<tr>"
			         +"<td><b> CITATION </b></td>"
			         +"<td>"+ citation + "</td>"
			     +"</tr>"
			         
	 +"<tr>"
	 +"<td><b> FACULTY NAME </b></td>"
	 +"<td>"+ fname + " " + lname + "</td>"
	+"</tr>"
    
			 
			 +"<tr>"
			 +"<td><b> FACULTY EMAIL ADDRESS </b></td>"
			 +"<td>"+ mail + "</td>"
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
								while (rs.next());
							}
						
			}
			
			// executes this code if the radio button selected is keyword.
			
			else if (radiosel.equals("keyword"))
			{	
				ResultSet rs=null;
				String sql = "select papers.paperid, papers.title, papers.abstract, papers.citation, Y.email, Y.fName, Y.lname from (Select X.facultyID, faculty.email, faculty.fname, faculty.lname, X.paperID from (select authorship.facultyID, paper_keywords.paperid from authorship join paper_keywords on authorship.paperid = paper_keywords.paperid where paper_keywords.keyword = '" + wordsearch + "' ) as X join faculty on X.facultyID = faculty.facultyID) as Y join papers on Y.paperID = papers.paperID";			
				rs = statement.executeQuery( sql );	
			
				if (!rs.next() ) 
				{
					response.sendRedirect("http://localhost:8080/DBConnection/NoResultFound.html");
				} 
			
				else
				{
				
				
				{
						do 
						{
							
						int paperid = rs.getInt(1);
						String title = rs.getString(2);	
						String abs = rs.getString(3);
						String citation = rs.getString(4);
						String mail = rs.getString(5);	
						String fname = rs.getString(6);
						String lname = rs.getString(7);
						
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
				        		               +"<td>"+ title + "</td>"
				        		           +"</tr>"
				        		           
					 +"<tr>"
			         +"<td><b> ABSTRACT </b></td>"
			         +"<td>"+ abs + "</td>"
			     +"</tr>"

					 +"<tr>"
			         +"<td><b> CITATION </b></td>"
			         +"<td>"+ citation + "</td>"
			     +"</tr>"

			 +"<tr>"
			 +"<td><b> FACULTY NAME </b></td>"
			 +"<td>"+ fname + " " + lname + "</td>"
			+"</tr>"

			     
			 +"<tr>"
			 +"<td><b> FACULTY EMAIL ADDRESS </b></td>"
			 +"<td>"+ mail + "</td>"
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
						while (rs.next());
								}
							}
						}
							
		}
			catch(Exception cnfe)
		{
			cnfe.printStackTrace();
		}
	}
}
	
