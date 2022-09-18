package endes;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomListServlet extends HttpServlet {
    public RoomListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //部屋の情報を扱うjavaBeanを作る
        RoomBean roomBean = new RoomBean();
        //getListで部屋一覧のデータを取得
        ArrayList<RoomBean> roomList = roomBean.getList();

        RequestDispatcher dispatcher;

        //取得したデータによる結果のページをディスパッチ
        if (roomList != null) {
            request.setAttribute("roomList", roomList);
            dispatcher = request.getRequestDispatcher("rooms.jsp");
        } 
        //エラー処理
        else {
            dispatcher = request.getRequestDispatcher("error.jsp");
        }
        
        //jspに処理を遷移させる
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //新しい部屋の情報を取得
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        System.out.println("Insert:  " + "name:" + name + ", description:" + description);
        
        //nameが存在しないときのエラー処理
        if( name == null || name.isEmpty() ) {
            response.sendError(400, "Bad Request: name is empty");
            return;
        }

        //descriptionが存在しないときの処理
        if( description == null ) {
            description = "";
        }

        //新しい部屋のデータを扱うjavaBeanの作成
        RoomBean roomBean = new RoomBean();
        //新しい部屋のデータを登録
        try {
            roomBean.insertRecord(name, description);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("rooms");
            return;
        }
        
        doGet(request, response);
    }

}