



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
/**
 * Servlet implementation class FinderSlt
 */
@WebServlet("/FinderSlt")
public class FinderSlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinderSlt() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
    		HttpSession session = request.getSession(true);
    		session.setMaxInactiveInterval(2*60);
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
	    
	    boolean torf = false; 
			
		System.out.println("Checking if login was complete");
		
		String user_name = (String)session.getAttribute("user_name");
		String password = (String)session.getAttribute("password");
		boolean torf1 = false;
		
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
                            		torf1 = true;
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
			
			
			 
		if(session!=null){
			System.out.println(torf1);
			if(torf1){
				System.out.println("Hello: " + user_name);
			}else {
	    			 response.getWriter().append("<!DOCTYPE html>\n" + 
	    			 		"<html>\n" + 
	    			 		"<head>" +
	    			 		"</head>" +
	    			 		"<body>"+
	    			 		"Please login first" +
	    			 		"</body>" +
	    			 		"</html>");  
	    			response.sendRedirect("index.html");
        		}
        }else{  
            	response.getWriter().append("<!DOCTYPE html>\n" + 
    			 		"<html>\n" + 
    			 		"<head>" +
    			 		"</head>" +
    			 		"<body>"+
    			 		"Please login first" +
    			 		"</body>" +
    			 		"</html>");  
            	response.sendRedirect("index.html");
        }  
        
			
			System.out.println("Test DB access ");

        
        
        Cereal c;
        ArrayList<Cereal> cereal = new ArrayList<Cereal>();
        //local read ok, write ok
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
                stat.execute("select * from cereal");

                System.out.println("connLocal=" + connLocal);


               
                
				String input = request.getParameter("input");
				String choice = request.getParameter("choice");
				String attribute = request.getParameter("attribute");
				boolean ifApplicable = false;
				if(choice.compareTo("Sugar") == 0 || choice.compareTo("Fiber") == 0 || choice.compareTo("price") == 0) {
				//            				if(!(attribute.compareTo("else") == 0)) {
					ifApplicable = true;
				//            				}
				}
				
//				sql1 = "select * from cereal";
                if(ifApplicable == true) {
					try {
	    					if(choice.compareTo("Sugar") == 0) {
	    						if(attribute.compareTo("EQ")==0) {
								sql1 = "select * from cereal where sugargram = '" + Integer.parseInt(input) +"'";
								torf = true;
							
							}else if(attribute.compareTo("GE")==0) {
								sql1 = "select * from cereal where sugargram >= '" + Integer.parseInt(input) +"'";
								torf = true;
							
							}else if(attribute.compareTo("LE")==0) {
								sql1 = "select * from cereal where sugargram <= '" + Integer.parseInt(input) +"'";
								torf = true;
							}		
	    	    				}else if(choice.compareTo("Fiber") == 0){
	    	    					int y;
	    	    					y = Integer.parseInt(input);
	    	    					if(attribute.compareTo("EQ")==0) {
	    	    						sql1 = "select * from cereal where fibergram = '" + y +"'";
	    							torf = true;
	    						}else if(attribute.compareTo("GE")==0) {
	    							sql1 = "select * from cereal where fibergram >= '" + y +"'";
	    							torf = true;
	    						}else if(attribute.compareTo("LE")==0) {
	    							sql1 = "select * from cereal where fibergram <= '" + y +"'";
	    							torf = true;
	    						}
	    	    				}else if(choice.compareTo("price") == 0){
	    	    					float z;
	    	    					z = Float.parseFloat(input);
	    	    					if(attribute.compareTo("EQ")==0) {
	    	    						sql1 = "select * from cereal where price = '" + z +"'";
	    							torf = true;
	    						}else if(attribute.compareTo("GE")==0) {
	    							sql1 = "select * from cereal where price >= '" + z +"'";
	    							torf = true;
	    						}else if(attribute.compareTo("LE")==0) {
	    							sql1 = "select * from cereal where price <= '" + z +"'";
	    							torf = true;	
	    						}
	    	    				}
					}catch(Exception e) {
						System.out.println("AN ERROR OCCURED");
					}
					
				}else if(input.compareTo("")!=0){
					if(choice.compareTo("Brand") == 0) {
						sql1 = "select * from cereal where brand like '%" + input +"%'";
						torf = true;
					}else if(choice.compareTo("Name") == 0){
						sql1 = "select * from cereal where name like '%" + input +"%'";
						torf = true;
					}else if(choice.compareTo("date") == 0){
						sql1 = "select * from cereal where expiredate like '%" + input +"%'";
						torf = true;
					}
				}
			
                
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
                            		c = new Cereal(rs.getInt("idcereal"),  rs.getString("brand"), rs.getString("name"), rs.getInt("sugargram"), rs.getInt("fibergram"), rs.getString("expiredate"), rs.getFloat("price"), rs.getString("productdescription"), rs.getInt("inventory"));
                            		cereal.add(c);
                                
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

            } catch (SQLException sqex)
            {
                sqex.printStackTrace();

            }//end try

            System.out.println(
                    "\nEnd local mysql");
        }
        
    		
			
		response.getWriter().append("<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<style>\n" + 
				"table {\n" + 
				"    border-collapse: collapse;\n" + 
				"    width: 100%;\n" + 
				"}\n" + 
				"\n" + 
				"td, th {\n" + 
				"    border: 1px solid black;\n" + 
				"    text-align: left;\n" + 
				"    padding: 8px;\n" + 
				"}\n" + 
				"\n" + 
				"tr:nth-child(even) {\n" + 
				"    background-color: #dddddd;\n" + 
				"}\n" + 
				"</style>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"\n" + 
				"<table>\n" + 
				"	<tbody>\n" + 
				"		<tr>\n" + 
				"			<td> idcreal </td>\n" + 
				"			<td> brand </td>\n" + 
				"			<td> name </td>\n" +
				"			<td> sugargram </td>\n" +
				"			<td> fibergram </td>\n" +
				"			<td> expiredate </td>\n" +
				"			<td> price </td>\n" +
				"			<td> productdescription </td>\n" +
				"			<td> inventory </td>\n" +
				"		</tr>\n");
		
		for(int i = 0; i < cereal.size(); i++) {
			response.getWriter().append("<tr>\n");
			response.getWriter().append(cereal.get(i).createTable());
			response.getWriter().append("</tr>\n");
		}
		if(torf == false) {
	 		response.getWriter().append("<h3>That does not exist try agian</h3>");
	 	}
	     response.getWriter().append(
	     "	</tbody>\n" + 
			"</table>\n" + 
			"\n" + 
			"</body>\n" + 
			"</html>\n" + 
			"\n" + 
			"");
		request.getRequestDispatcher("search.html").include(request, response);
		
    
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
