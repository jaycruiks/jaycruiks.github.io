

import java.io.IOException;
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

/**
 * Servlet implementation class regSlt
 */
@WebServlet("/regSlt")
public class regSlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public regSlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
         
        boolean doRead = false;
        boolean doWrite = true;

        boolean csmysql = false;
         
        String name = request.getParameter("name");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String cc_number = request.getParameter("cc_number");
		String password = request.getParameter("password");
		
		System.out.println("Name: " + name);
		System.out.println("address: " + address);
		System.out.println("email: " + email);
		System.out.println("cc_number: " + cc_number);
		System.out.println("password: " + password);
		
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
            stat = connLocal.createStatement();
            stat.execute("select * from people");
            if (doWrite){
                System.out.println("start write");

                sql1 = "insert into people(name, address, email, cc_number, password)" 
                		+"VALUES('" +name+"', '"+address+"', '"+email+"', '"+cc_number+"', '"+password+ "');";
                int numberUpdated = stat.executeUpdate(sql1);
                System.out.print("numberUpdated=" + numberUpdated);
                System.out.print("end write");

            }
            if (stat != null){
                stat.close();
            }
            
        }catch (SQLException sqex)
        {
            for (Throwable thro : sqex)
            {
                thro.printStackTrace();
            }
        }
        
        request.getRequestDispatcher("index.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
