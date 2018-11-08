package example;

import java.util.*;
import java.sql.*;

public class Database {
	
	private UserInfo userinfo;
	private Connection conn;
	private PreparedStatement pstmt;
	
	private Database() {
		try {
			Class.forName(Constants.JDBC_DRIVER);
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASSWORD);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private static class Singleton{
		private static final Database instance = new Database();
	}
	
	public static Database getInstance() {
		return Singleton.instance;
	}
	
	public boolean insert(String tableName, List values) {
		List columns = new ArrayList();
		if(insert(tableName, columns, values)) return false;
		return true;
	}
	public boolean insert(String tableName, List columns, List values) {
		String sql = "insert into ";
		String columnNames = insertCommaWithColumns(columns);
		String valuesNum = insertCommaWithQm(values);
		
		if(columns.size() != 0) {
			if(columns.size() != values.size()) {
				System.out.println("컬럼이랑 속성값이랑 갯수가 안맞아");
				return false;
			}
			sql += tableName + "(" + columnNames + ") values(" + valuesNum + ")";
		}else sql += tableName + " values(" + valuesNum + ")";
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < values.size() ; i++) {
				pstmt.setString(i + 1, values.get(i).toString());
			}
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException SQLe) {
			System.out.println(SQLe);
		}
		return true;
	}
	
	public boolean update(List tables, List columns, List values) {
		String sql = "update ";
		//테이블이름 넣고, 컬럼 네임 넣고, 벨류들 넣으면 바뀐다
		/*
			개선 예정
		 */
		return true;
	}
	
	public boolean delete(String table) {
		List tables = new ArrayList();
		tables.add(table);
		if(delete(tables)) return true;
		else return false;
	}
	public boolean delete(List tables) {
		String conditions = "";
		List values = new ArrayList();
		if(delete(tables,conditions,values)) return true;
		else return false;
	}
	public boolean delete(List tables, String conditions, List values) {
		//테이블이름 넣고, 인스턴스 값을 넣으면 그 로우가 지워진다
		String sql = "delete from "; 
		String tableNames = insertCommaWithColumns(tables);
		String valuesNum = insertCommaWithQm(values);
		StringBuffer bufferClone = new StringBuffer(conditions);
		int numberOfQm = 0;//물음표 갯수
		
		while(bufferClone.indexOf("?") != -1) {
			bufferClone.deleteCharAt(bufferClone.indexOf("?"));
			numberOfQm++;
		}
		if(numberOfQm != values.size()) {
			System.out.println("넣어야될 값이랑 물음표갯수가 다르잖아");
			return false;
		}
		try {
			if(values.size() != 0) {
				sql += tableNames + " where " + conditions;
				pstmt = conn.prepareStatement(sql);
				for(int i = 0; i < numberOfQm; i++) {
					pstmt.setString(i+1, values.get(i).toString());
				}
			}else {
				sql += tableNames;
				pstmt = conn.prepareStatement(sql);
			}
		}catch(SQLException SQLe) {
			System.out.println(SQLe);
		}
		return true;
	}
	
	public List select(String tableName) {
		List columns = new ArrayList();
		return select(columns, tableName);
	}
	public List select(String column, String tableName) {
		List columns = new ArrayList();
		columns.add(column);
		return select(columns, tableName);
	}
	public List select(List columns, String tableName) {
		String conditions = "";
		List tables = new ArrayList();
		List values = new ArrayList();
		tables.add(tableName);
		return select(columns, tables, conditions, values);
	}
	public List select(List columns, List tables, String conditions, String value) {
		List values = new ArrayList();
		values.add(value);
		return select(columns, tables, conditions, values);
	}
	public List select(List columns, List tables, String conditions, List values) {
		//컬럼네임 넣고, 테이블이름 넣고, 벨류들 넣으면 그 값들이 Map타입으로 반환된다.
		String sql = "select ";
		String columnNames = insertCommaWithColumns(columns);
		String tableNames = insertCommaWithColumns(tables);
		StringBuffer bufferClone = new StringBuffer(conditions);
		int numberOfQm = 0;
		List<Map> result = new ArrayList<Map>();
		
		while(bufferClone.indexOf("?") != -1) {
			bufferClone.deleteCharAt(bufferClone.indexOf("?"));
			numberOfQm++;
		}
		if(numberOfQm != values.size()) {
			System.out.println("넣어야될 값이랑 물음표갯수가 다르잖아");
			return null;
		}
		try {
			if(columns.size() != 0) {
				String clone = conditions.toString();
				if(!(conditions.equals(""))) {
					clone = " where " + clone;
				}
				sql += columnNames + " from " + tableNames + clone;
				pstmt = conn.prepareStatement(sql);
				for(int i = 0; i < numberOfQm; i++) {
					pstmt.setString(i+1, values.get(i).toString());
				}
			}else {
				sql += "*" + " from " + tableNames;
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metadata = rs.getMetaData();
			if(rs.next()) {
				Map index = new HashMap();
				for(int i = 0; i < metadata.getColumnCount(); i++) {
					index.put(metadata.getColumnLabel(i+1).toString(), rs.getObject(metadata.getColumnLabel(i+1).toString()));
				}
				result.add(index);
			}
			pstmt.close();
		}catch(Exception SQLe) {
			System.out.println(SQLe);
		}
		return result;
	}
	
	private String insertCommaWithColumns(List original) {
		StringBuilder result = new StringBuilder("");
		for(int i = 0; i < original.size(); i++) {
			result.append(original.get(i).toString()+ ",");
		}
		if(result.length() != 0) {
			result.deleteCharAt(result.length()-1);	
		}
		return result.toString();
	}
	private String insertCommaWithQm(List original) {
		StringBuilder result = new StringBuilder("");
		for(int i = 0; i < original.size(); i++) {
			result.append("?,");
		}		
		if(result.length() != 0) {
			result.deleteCharAt(result.length()-1);	
		}
		return result.toString();
	}
	
}
