package endes;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomHistoryServlet extends HttpServlet {
    public RoomHistoryServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdParameter = request.getParameter("id");

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

        RoomBean roomBean = new RoomBean();
        RoomBean room = roomBean.find(roomId);
        if (room == null) {
            System.out.println("id:" + roomId + " のルームが見つかりません");
            dispatcher = request.getRequestDispatcher("roomNotFound.jsp");
            dispatcher.forward(request, response);
            return;
        }

        NumberOfPeopleBean nBean = new NumberOfPeopleBean();
        ArrayList<NumberOfPeopleBean> numberOfPeopleList = nBean.getListByRoomId(roomId);
        if (numberOfPeopleList == null) {
            dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        } 

        request.setAttribute("room", room);
        request.setAttribute("numberOfPeopleList", numberOfPeopleList);
        dispatcher = request.getRequestDispatcher("roomHistory.jsp");
        dispatcher.forward(request, response);
    }
}
