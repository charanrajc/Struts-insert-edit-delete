package java4s;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;


public class Display extends ActionSupport implements ServletRequestAware{	
	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request;
	
	
	public String execute()
	{
	
	try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		java.sql.Connection con =DriverManager.getConnection("jdbc:sqlserver://localhost:1501;" + "databaseName=Training;user=sa;password=Admin123");
	Statement st=con.createStatement(); 
	ResultSet rs = st.executeQuery("select * from details");
	
    	List<Mybean> li = null;
    	li = new ArrayList<Mybean>();   
    	Mybean mb = null;
    
		while(rs.next()) 
			{ 	
			    mb = new Mybean();
			 
			    mb.setNo(rs.getInt("sno"));
			    mb.setNam(rs.getString("sname"));
			    mb.setCt(rs.getString("scountry"));		  
			    
			    li.add(mb);
			    
			}
			
		request.setAttribute("disp", li);
		
		rs.close(); 
 		st.close(); 
		con.close();

		    } 
		catch(Exception e){ 
 			e.printStackTrace(); 
 		}

		
			return SUCCESS;
		
	}

	public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getServletRequest() {
        return request;
}
	
}
