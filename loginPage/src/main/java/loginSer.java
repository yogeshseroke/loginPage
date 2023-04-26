

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginSer
 */
@WebServlet("/loginSer")
public class loginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public loginSer() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out=response.getWriter();
		try {
			String uid=request.getParameter("txtuid");
			String pass=request.getParameter("txtpass");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/feedbacksystem","root","");
            Statement st=con.createStatement();
            ResultSet x=st.executeQuery("select * from `admin` where `userid`='"+uid+"' and `password`='"+pass+"'");
            if(x.next())
            {
            	HttpSession session=request.getSession();
            	session.setAttribute("uid",uid);
            	response.sendRedirect("adminDash.jsp");
            }
            else {
            	response.sendRedirect("login.jsp");
            }
		}
		catch(Exception ex)
		{
			out.println(ex.getMessage().toString());
		}
	}

}
