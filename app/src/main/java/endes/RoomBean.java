package endes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Room モデルを表すクラス
* @author Ogi
*/
public class RoomBean {
    private int id;
    private String name;
    private String description;

    /**
     * 部屋の id を取得する
     * @return Room ID
     */
    public int getId() {
        return id;
    }
    /**
     * 部屋の id を設定する
     * @param i int 型の room id
     */
    public void setId(int i) {
        id = i;
    }

    /**
     * 部屋の名前を取得する
     * @return Room name
     */
    public String getName() {
        return name;
    }
    /**
     * 部屋の名前を設定する
     * @param n String 型の room 名
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * 部屋の説明を取得する
     * @return Room description
     */
    public String getDescription() {
        return description;
    }
    /**
     * 部屋の説明を設定する
     * @param d String 型の room の説明
     */
    public void setDescription(String d) {
        description = d;
    }
    
    /**
     * Room のリストをデータベースから取得する
     * @return RoomBean のリスト エラーが発生した場合はnull
     */
    public ArrayList<RoomBean> getList() {
        ArrayList<RoomBean> rooms = new ArrayList<RoomBean>();
        
        try {
            Connection connection = DBManager.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM room ORDER BY id desc limit 10";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                RoomBean room = new RoomBean();
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setDescription(resultSet.getString("description"));
                rooms.add(room);
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            
            return rooms;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Room をデータベースからidで検索して取得する
     * @param id int 型の room id
     * @return 受け取ったidを持つRoomBean エラーが発生した場合はnull
     */
    public RoomBean find(int id) {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "SELECT * FROM room WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            RoomBean room = new RoomBean();
            if (resultSet.next()) {
                System.out.println(resultSet);
                room.setId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setDescription(resultSet.getString("description"));
            } else {
                System.out.println("id:"+ id + "のRoomはNULLです");
                room = null;
            }
            
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("id:" + room.getId() + ", name:" + room.getName() + ", description:" + room.getDescription());
            
            return room;
        } catch (Exception e) {
            System.out.println("id:"+ id + "のRoomはNULLです");
            return null;
        }
    }

    /**
     * データベースにRoomを追加する
     * @param name String 型の room 名 
     * @param description String 型の room の説明
     */
    public void insertRecord(String name, String description) throws Exception {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "insert into room (name, description) values (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, description);

            int count = statement.executeUpdate();
            statement.close();
            connection.close();

            if (count > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                throw new Exception("Record not inserted.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
