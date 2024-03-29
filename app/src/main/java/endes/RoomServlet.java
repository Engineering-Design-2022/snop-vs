package endes;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
* `/room` パスで呼ばれる RoomServlet クラス
* @author lemonadern
*/
public class RoomServlet extends HttpServlet {
    public RoomServlet() {
        super();
    }

    /**
     * GET リクエストを受け取ったときの処理
     * リクエストの id パラメタを取得し、id が該当する Room と NumberOfPeople をデータベースから取得して room.jsp に渡す
    * @param request サーブレット用のHTTPリクエスト
    * @param response サーブレット用のHTTPレスポンス
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdParameter = request.getParameter("id");
        System.out.println(roomIdParameter);

        // id パラメタがなければ、ルーム一覧にリダイレクトする
        if (roomIdParameter == null) {
            response.sendRedirect("rooms");
            return;
        }

        int roomId;
        try {
            roomId = Integer.parseInt(roomIdParameter);
        } catch (Exception e) {
            response.sendRedirect("rooms");
            return;
        }

        RequestDispatcher dispatcher;

        // Room 
        RoomBean roomBean = new RoomBean();
        RoomBean room = roomBean.find(roomId);

        if( room == null) {
            System.out.println("id:" + roomId + " のルームが見つかりません");
            dispatcher = request.getRequestDispatcher("roomNotFound.jsp");
            dispatcher.forward(request, response);
            return;
        }

        NumberOfPeopleBean nBean = new NumberOfPeopleBean();
        NumberOfPeopleBean numberOfPeople = nBean.findLatestByRoomId(roomId);
        if (numberOfPeople == null) {
            System.out.println("人数が見つかりません");
            dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        request.setAttribute("room", room);
        request.setAttribute("numberOfPeople", numberOfPeople);
        dispatcher = request.getRequestDispatcher("room.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * POST リクエストを受け取ったときの処理
     * リクエストの id, number パラメタ を取得し、NumberOfPeople を データベースに登録する
    * @param request サーブレット用のHTTPリクエスト
    * @param response サーブレット用のHTTPレスポンス
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdParameter = request.getParameter("roomId");

        if (roomIdParameter == null) {
            response.sendError(400, "Bad Request: roomId is empty");
            return;
        }

        int roomId;
        try {
            roomId = Integer.parseInt(roomIdParameter);
        } catch (Exception e) {
            response.sendError(400, "Bad Request: roomId is not integer");
            return;
        }

        String numberParameter = request.getParameter("number");

        if (numberParameter == null) {
            response.sendError(400, "Bad Request: number is empty");
            return;
        }

        int number;
        try {
            number = Integer.parseInt(numberParameter);
        } catch (Exception e) {
            response.sendError(400, "Bad Request: number is not integer");
            return;
        }

        NumberOfPeopleBean nBean = new NumberOfPeopleBean();
        try {
            nBean.insertRecord(number, roomId);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Internal Server Error");
            return;
        }
        
        response.sendRedirect("room?id=" + roomId);
    }
}