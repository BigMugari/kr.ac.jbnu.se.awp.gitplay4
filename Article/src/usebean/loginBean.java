package usebean;

import java.sql.*;

public class loginBean {

    Connection con;
    
    public loginBean()
    {   
          try
          {
               Class.forName("com.mysql.jdbc.Driver");
               String url="jdbc:mysql://localhost:3306/userdb?characterEncoding=euckr";
               String id="root";
               String pwd="1234";
               con=DriverManager.getConnection(url,id,pwd);
               System.out.println("db연동 성공");
       

           }catch(Exception e)
          {
               e.printStackTrace();
          }
   
     }//생성자

    public Connection getCon()
    {
          if(con==null)

          {
                 new loginBean();
           }
     

           return con;
    }//getCon()
    
    public void setCon(String driver,String url, String dboid, String dbopwd)
    {
          try{
               Class.forName(driver);
               con=DriverManager.getConnection(url,dboid,dbopwd);
   
          }catch(Exception e)
          {
               e.printStackTrace();
           }
     }// setCon
	
    
    public ResultSet login(Connection conn, ResultSet rs, PreparedStatement pstmt, String id, String pw) {

    	try {
    		pstmt = conn.prepareStatement("select * from tb_user");
    		rs = pstmt.executeQuery();
    		
    		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return rs;
    }
}
