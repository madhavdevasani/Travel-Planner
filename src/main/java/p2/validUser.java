package p2;
import p1.DbConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class validUser
 */
@WebServlet("/validUser")
public class validUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public validUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        try {
            String username = request.getParameter("username");
            int pwd = Integer.parseInt(request.getParameter("password"));
            
            // Assuming DbConnection.Connection() returns a Statement object
            Statement st = DbConnection.Connection();
            String q1="select * from register where username='"+username+"' AND password="+pwd;
            ResultSet rs=st.executeQuery(q1);
            if(rs.next())
            {
            	HttpSession session=request.getSession();
            	session.setAttribute("LoggedIn",true);
                response.sendRedirect("home.html");
            }
            else {
                response.sendRedirect("Login.html?error=true");
            }
        } catch(Exception e) {
            out.println(e);
        }
    }
    
}
