package web;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class of the program acting as a servlet.
 */
@WebServlet(urlPatterns = {"/dir"})
public class MyServlet extends HttpServlet {

    @EJB
    Bean myBean;

    /**
     * Overriding the doGet method from the HttpServlet class. Handles GET requests arriving
     * at the URL "/ dir" with the argument "path".
     * If the path is set incorrectly, returns the home directory.
     *
     * @param request  - Extends the ServletRequest interface to provide request information for HTTP servlets.
     * @param response - Extends the ServletResponse interface to provide HTTP-specific functionality in sending
     *                 a response.
     * @throws IOException - Throws if the * .jsp file is not found.
     * @throws ServletException - Defines a general exception a servlet can throw when it encounters difficulty.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String directory = request.getParameter("path");
        directory = myBean.createFolderPath(directory);
        System.out.println(">> " + directory);
        ArrayList<String> foldersList = myBean.recursiveDirectory(directory);
        request.setAttribute("foldersList", foldersList);
        getServletContext().getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);
    }
}


