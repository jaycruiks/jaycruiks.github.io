

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginSLT
 */
@WebServlet("/loginSLT")
public class loginSLT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginSLT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();  
		HttpSession session=request.getSession(); 
		session.setMaxInactiveInterval(2*60);
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		
		boolean torf = false;
		
		System.out.println("Test DB access ");

        Connection connRemote = null;
        Connection connLocal = null;
        Properties props;

        Driver myDriver;
        String dbLocal;
        String dbUrlRemote;

        Statement stat;

        Connection conn = null;

        ResultSet rs = null;
        String sql1 = null;

        //sample data
        String nameStr;
        String ageStr;
        
        boolean doRead = true;
        boolean doWrite = false;

        boolean csmysql = false;  //test cs-mysql
        
        if (!csmysql)
        {
            //OPEN and READ
            System.out.println("access  local mysql");
            try
            {
            		
                props = new Properties();
                props.put("user", "root");
                props.put("password", "garrett");  //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ delete pwd
                
                myDriver = new com.mysql.jdbc.Driver();
                DriverManager.registerDriver(myDriver);
                
                
                System.out.println("****myDriver=" + myDriver);

                dbLocal = "jdbc:mysql://localhost:3306/cereal?useSSL=false";

                connLocal = DriverManager.getConnection(dbLocal, props);

                // just to test whole path to db
                stat = connLocal.createStatement();
                stat.execute("select * from people");

                System.out.println("connLocal=" + connLocal);


                sql1 = "SELECT name, password FROM people WHERE name = '" + user_name + "' AND password = '" + password+"'";
                if (stat != null)
                {
                	
                    rs = stat.executeQuery(sql1);

                    if (doRead)
                    {
                        System.out.println("start read");

                        if (rs != null)
                        {
                            while (rs.next())
                            {
                            		torf = true;
                            		System.out.println(rs.getString("name") + " " + rs.getString("password"));
                            		//##################################################//
                                
                            }
                        }
                        
                        System.out.println("end read");
                    }
                }

                if (rs != null)
                {
                    rs.close();
                }
                if (stat != null)
                {
                    stat.close();
                }

            } catch (SQLException sqex){
                sqex.printStackTrace();

            }//end try

            System.out.println(
                    "\nEnd local mysql");
        }
		
        System.out.println(torf);
		if(torf){  
			  
			
			session.setAttribute("user_name", user_name);
			session.setAttribute("password",password); 
			
			request.getRequestDispatcher("search.html").include(request, response);
        }else {
        		
			session.setAttribute("password","*"); 
        		request.getRequestDispatcher("index.html").include(request, response);
        		out.append("Try Agian");
		}
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
