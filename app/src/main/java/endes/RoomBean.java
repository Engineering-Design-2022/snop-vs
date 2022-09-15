package endes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomBean {
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }
    public void setId(int i) {
        id = i;
    }

    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String d) {
        description = d;
    }
    
    public ArrayList<RoomBean> getList() {
        ArrayList<RoomBean> rooms = new ArrayList<RoomBean>();
        
        try {
            Connection connection = DBManager.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM room";
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

    public void insertRecord() throws Exception {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "INSERT INTO ROOMS (NAME, DESCRIPTION) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, description);

            int count = statement.executeUpdate();
            statement.close();
            connection.close();

            if (count > 0) {
                System.out.println("Record inserted successfully.");
            } else {
                System.out.println("Record not inserted.");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
