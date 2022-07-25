package endes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class roomBean {

	private int id;
	private String name;
	private String desc;
	
	//部屋の番号
	public	void setId(int i) {
		id = i;
	}
	public int getId() {
		return id;
	}
	
	//部屋の名前
	public void setName(String nm) {
		name = nm;
	}
	public String getName() {
		return name;
	}
	
	//説明
	public void setDesc(String dc) {
		desc = dc;
	}
	public String getDesc() {
		return desc;
	}
	
	//データベースへの追加
	public boolean insertRecord() {
		
		try {
			Connection con = DBServlet.getUserConnection();
			Statement smt = con.createStatement();
			int count = smt.executeUpdate(
					"INSERT INTO Room (Id,Name,Description) VALUES"
					+ "(" + id + ",'" + name + "'," + desc + ")" );
			smt.close();
			con.close();
			if (count>0) 
				return true;
			else 
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//データベースからの削除
	public boolean deleteRecord() {
		
		try {
			Connection con = DBServlet.getUserConnection();
			
			String sql = "DELETE FROM Room Where Id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1,id);
			int count = ps.executeUpdate();
			ps.close();
			con.close();
			if (count>0) 
				return true;
			else 
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
