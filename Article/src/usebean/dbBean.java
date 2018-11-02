package usebean;

import java.sql.*;

 

public class dbBean

{
     Connection con;
 
     private String userid;
 	private String passwd;
     
     public dbBean()
     {   
           try
           {
                Class.forName("com.mysql.jdbc.Driver");
                String url="jdbc:mysql://localhost:3306/articles?characterEncoding=euckr";
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
                  new dbBean();
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
    
    public ResultSet show(Connection conn, ResultSet rs, PreparedStatement pstmt) {
    	// String whereSQL = "";
    	
    	try {
			// 게시물 목록을 얻는 쿼리 실행
			pstmt = conn.prepareStatement("SELECT NUM, SUBJECT, WRITER, REG_DATE, HIT FROM TB_article");
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		return rs;
    }
    
    public ResultSet total(Connection conn, ResultSet rs, PreparedStatement pstmt) {
    	// String whereSQL = "";
    	
    	try {
    		// 게시물 총 수를 얻는 쿼리 실행
			pstmt = conn.prepareStatement("SELECT COUNT(NUM) AS TOTAL FROM TB_article");
			rs = pstmt.executeQuery();
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		return rs;
    }
    
    public void hit(Connection conn, ResultSet rs, PreparedStatement pstmt, String num) {
    	// String whereSQL = "";
    	
    	try {
    		// 조회수 증가 쿼리 실행
    		pstmt = conn.prepareStatement("UPDATE TB_article SET HIT = HIT + 1 WHERE NUM = ?");
    		pstmt.setString(1, num);
    		pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ResultSet detail(Connection conn, ResultSet rs, PreparedStatement pstmt, String num) {
    	// String whereSQL = "";
    	
    	try {
    		pstmt = conn.prepareStatement(
    				"SELECT NUM, SUBJECT, CONTENTS, WRITER, HIT, REG_DATE FROM TB_article "+ 
    				"WHERE NUM = ?");
    			pstmt.setString(1, num);
    			rs = pstmt.executeQuery();
    			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		return rs;
    }
    
    public void delete(Connection conn, PreparedStatement pstmt, String num) {
    	// String whereSQL = "";
    	
    	try {
    		pstmt = conn.prepareStatement(
    				"delete from TB_article where num = ?");
    			pstmt.setString(1, num);
    			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void write(Connection conn, PreparedStatement pstmt, String subject, String writer, String contents) {
    	// String whereSQL = "";
    	
    	try {
			pstmt = conn.prepareStatement(
					"INSERT INTO TB_article (SUBJECT, WRITER, CONTENTS, HIT, REG_DATE) "+
					"VALUES (?, ?, ?, 0, NOW())");
				pstmt.setString(1, subject);
				pstmt.setString(2, writer);
				pstmt.setString(3, contents);
				pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public ResultSet login(Connection conn, ResultSet rs, PreparedStatement pstmt) {

    	try {
    		String sql = "select * from tb_user";
    		pstmt = conn.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

    	
    }
    
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
    

    
    
    
    
}//class