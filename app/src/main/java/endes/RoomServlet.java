package endes;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomServlet extends HttpServlet {
    public RoomServlet() {
        super();
    }

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
            System.out.println("ルームが見つかりません");
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
}
