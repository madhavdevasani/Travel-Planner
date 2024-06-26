package p2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import p1.DbConnection;

/**
 * Servlet implementation class Planning
 */
@WebServlet("/Planning")
public class Planning extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Planning() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TravelPlanner","root","tiger");
			Statement st=con.createStatement();
			Enumeration<String>p1=request.getParameterNames();
			String id=p1.nextElement();
			if(id.equals("id"))
			{
			int id1=Integer.parseInt(request.getParameter("id"));
			String fullname=request.getParameter("fullname");
			String email=request.getParameter("email");
			String username=request.getParameter("username");
			int  pwd=Integer.parseInt(request.getParameter("password"));
			String q1="insert into register values("+id1+",'"+fullname+"','"+email+"','"+username+"',"+pwd+")";
			st.execute(q1);
			out.println("Account created successfully");
			}
			else if(id.equals("book"))
			{
				HttpSession session=request.getSession();
				Boolean isLoggedIn=(Boolean)session.getAttribute("LoggedIn");
				if(isLoggedIn!=null && isLoggedIn ) {
				int id2=Integer.parseInt(request.getParameter("book"));
				String firstname=request.getParameter("firstname");
				String lastname=request.getParameter("lastname");
				int age=Integer.parseInt(request.getParameter("age"));
				String source=request.getParameter("source");
				String destination=request.getParameter("destination");
				String q1="insert into booking values("+id2+",'"+firstname+"','"+lastname+"',"+age+",'"+source+"','"+destination+"')";
				st.execute(q1);
				out.println("Details Recorded");
				}
				else {
					out.println("Please Login firsr");
				}
					
			}
			else if(id.equals("show"))
			{
				out.println("booking details");
				int id3=Integer.parseInt(request.getParameter("show"));
				String q1="select * from booking where id="+id3;
				ResultSet rs=st.executeQuery(q1);
				while(rs.next())
				{
					out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getInt(4)+","+rs.getString(5)+","+rs.getString(6));
				}
				out.println();
			}
			
	}
		catch(Exception e)
		{
			out.println(e);
		}
	}
}
