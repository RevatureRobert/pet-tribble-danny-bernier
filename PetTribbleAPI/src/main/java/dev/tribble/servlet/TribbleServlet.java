package dev.tribble.servlet;

import dev.tribble.database.TribbleDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TribbleServlet extends HttpServlet {

    private final TribbleDAO TRIBBLE_DAO;

    public TribbleServlet(){
        this.TRIBBLE_DAO = new TribbleDAO();
    }

    /**
     * Handles all GET requests to this endpoint
     * @param req The HTTP Request being made
     * @param resp The HTTP Response associated with this request
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    /**
     * Handles all POST requests to this endpoint
     * @param req The HTTP Request being made
     * @param resp The HTTP Response associated with this request
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * Handles all PUT requests to this endpoint
     * @param req The HTTP Request being made
     * @param resp The HTTP Response associated with this request
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    /**
     * Handles all DELETE requests to this endpoint
     * @param req The HTTP Request being made
     * @param resp The HTTP Response associated with this request
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
