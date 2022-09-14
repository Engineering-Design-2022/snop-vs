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
        RoomBean roomBean = new RoomBean();
        ArrayList<RoomBean> roomList = roomBean.getList();

        RequestDispatcher dispatcher;

        if (roomList != null) {
            request.setAttribute("roomList", roomList);
            dispatcher = request.getRequestDispatcher("rooms.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("error.jsp");
        }
        
        dispatcher.forward(request, response);
    }
}
