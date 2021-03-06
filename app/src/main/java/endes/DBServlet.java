package endes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DBServlet extends HttpServlet {
    static final String DB_URL = "jdbc:postgresql://postgres:5432/docker";
    static final String DB_USER = "postgres";
    static final String DB_PASS = "password";
    static final String QUERY = "SELECT id,name FROM book";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        response.setContentType("text/html; charset=UTF-8");
        try {
            Class.forName("org.postgresql.Driver");
            try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(QUERY);
                PrintWriter pw = response.getWriter();) {
                pw.println("<html>");
                pw.println("<head><title>show table</title></head>");
                pw.println("<body><table border=1>");
                pw.println("<tr><th>id</th><th>name</th></tr>");
                while (rs.next()) {
                    pw.println("<tr>");
                    pw.println("<td>" + rs.getInt("id") + "</td>");
                    pw.println("<td>" + rs.getString("name") + "</td>");
                    pw.println("</tr>");
                }
                pw.println("</table></body>");
                pw.println("</html>");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
