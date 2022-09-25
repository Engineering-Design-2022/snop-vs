package endes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 部屋の人数(NumberOfPeople) モデルを表すクラス
 * @author yuto_ka
 */
public class NumberOfPeopleBean{
    private int id;
    private int number;
    private Timestamp createdAt;
    private int roomId;

    /**
     * id を取得する
     * @return NumberOfPeople の ID
     */
    public int getId() {
        return id;
    }
    /**
     * id を設定する
     * @param i int 型の id
     */
    public void setId(int i) {
        id = i;
    }

    /**
     * 部屋の人数を取得する
     * @return 部屋の人数
     */
    public int getNumber() {
        return number;
    }
    /**
     * 部屋の人数を設定する
     * @param n int 型の 部屋の人数
     */
    public void setNumber(int n) {
        number = n;
    }

    /**
     * NumberOfPeopleが登録された時刻を取得する
     * @return NumberOfPeopleが登録された時刻
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * NumberOfPeopleが登録された時刻をJSTに変換した文字列を取得する
     * @return 時刻を表す文字列
     */
    public String getCreatedAtString() {
        LocalDateTime gmtLocal = createdAt.toLocalDateTime();
        ZonedDateTime gmtZoned = gmtLocal.atZone(ZoneId.of("GMT", ZoneId.SHORT_IDS));
        ZonedDateTime jstZoned = gmtZoned.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        LocalDateTime jstLocal = jstZoned.toLocalDateTime();
        DateTimeFormatter datetimeformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateString = jstLocal.format(datetimeformatter);

        return dateString;
    }

    /**
     * NumberOfPeopleが登録された時刻を設定する
     * @param c Timestamp 型の NumberOfPeopleが登録された時刻
     */
    public void setCreatedAt(Timestamp c) {
        createdAt = c;
    }

    /**
     * 部屋のIDを取得する
     * @return NumberOfPeopleに紐付いている部屋のID
     */
    public int getRoomId() {
        return roomId;
    }

    /**
     * 部屋のIDを設定する
     * @param r int 型の 部屋のID
     */
    public void setRoomId(int r) {
        roomId = r;
    }

    /**
     * 部屋の一番最近の人数を取得する
     * @param roomId int 型の 部屋のID
     * @return NumberOfPeople
     */
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

    /**
     * 部屋の人数の履歴を取得する
     * @param roomId int 型の 部屋のID
     * @return 渡された roomId を持つ NumberOfPeole の ArrayList
     */
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

    /**
     * 部屋の人数を登録する
     * @param number int 型の 部屋の人数
     * @param roomId int 型の 部屋のID
     */
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