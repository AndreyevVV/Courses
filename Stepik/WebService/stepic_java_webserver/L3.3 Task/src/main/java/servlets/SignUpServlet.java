package servlets;

import DBService.DBService;
import DBService.DBException;
import DBService.DataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final DBService dbService;

    public SignUpServlet(DBService accountService) {
        this.dbService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        UsersDataSet user = new UsersDataSet(login, pass);

        try {
            long userId = dbService.addNewUser(user.getLogin(), user.getPassword());
            System.out.println("Added user id: " + userId);
        } catch (DBException e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
