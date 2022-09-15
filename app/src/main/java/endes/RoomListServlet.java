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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        System.out.println("Insert:  " + "name:" + name + ", description:" + description);
        
        if( name == null || name.isEmpty() ) {
            response.sendError(400, "Bad Request: name is empty");
            return;
        }

        if( description == null ) {
            description = "";
        }

        RoomBean roomBean = new RoomBean();
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
