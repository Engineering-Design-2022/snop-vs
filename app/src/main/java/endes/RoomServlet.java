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
        String roomId = request.getParameter("id");

        // id パラメタがなければ、ルーム一覧にリダイレクトする
        if (roomId == null) {
            response.sendRedirect("rooms");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("room.jsp");
        dispatcher.forward(request, response);
    }
}
