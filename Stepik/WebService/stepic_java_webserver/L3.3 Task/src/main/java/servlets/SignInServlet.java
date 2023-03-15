package servlets;

import DBService.DataSets.UsersDataSet;
import DBService.DBException;
import DBService.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        response.setContentType("text/html;charset=utf-8");

        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            UsersDataSet dataSet = dbService.getUserByNmae(login);
            System.out.println("User data set: " + dataSet);

            if (dataSet == null || !dataSet.getPassword().equals(pass)) {
                response.getWriter().println("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            response.getWriter().println(String.format("Authorized: %s", dataSet.getLogin()));
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
