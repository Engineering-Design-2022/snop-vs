package endes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class NumberOfPeopleBean{
    private int id;
    private int number;
    private Timestamp createdAt;
    private int roomId;

    public int getId() {
        return id;
    }
    public void setId(int i) {
        id = i;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int n) {
        number = n;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp c) {
        createdAt = c;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int r) {
        roomId = r;
    }

    public NumberOfPeopleBean findLatestByRoomId(int roomId) {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "SELECT id, number, created_at, room_id FROM number_of_people where room_id = ? order by created_at desc limit 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);
            
            ResultSet resultSet = statement.executeQuery();

            NumberOfPeopleBean numberOfPeople = new NumberOfPeopleBean();
            while (resultSet.next()) {
                numberOfPeople.setId(resultSet.getInt("id"));
                numberOfPeople.setNumber(resultSet.getInt("number"));
                numberOfPeople.setCreatedAt(resultSet.getTimestamp("created_at"));
                numberOfPeople.setRoomId(resultSet.getInt("room_id"));
            }
            resultSet.close();
            statement.close();
            connection.close();

            return numberOfPeople;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<NumberOfPeopleBean> getListByRoomId(int roomId) {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "SELECT id, number, created_at, room_id FROM number_of_people where room_id = ? order by created_at desc";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, roomId);

            ResultSet resultSet = statement.executeQuery();

            ArrayList<NumberOfPeopleBean> numberOfPeopleList = new ArrayList<NumberOfPeopleBean>();
            while (resultSet.next()) {
                NumberOfPeopleBean numberOfPeople = new NumberOfPeopleBean();
                numberOfPeople.setId(resultSet.getInt("id"));
                numberOfPeople.setNumber(resultSet.getInt("number"));
                numberOfPeople.setCreatedAt(resultSet.getTimestamp("created_at"));
                numberOfPeople.setRoomId(resultSet.getInt("room_id"));
                numberOfPeopleList.add(numberOfPeople);
            } 

            resultSet.close();
            statement.close();
            connection.close();

            return numberOfPeopleList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertRecord(int number, int roomId) throws Exception {
        try {
            Connection connection = DBManager.getDatabaseConnection();
            String query = "INSERT INTO number_of_people (number, room_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, number);
            statement.setInt(2, roomId);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }   

}