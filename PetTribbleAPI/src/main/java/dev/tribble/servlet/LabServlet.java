package dev.tribble.servlet;

import com.google.gson.Gson;
import dev.tribble.database.LabDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LabServlet extends HttpServlet {

    private final LabDAO LAB_DAO;

    public LabServlet(){
        this.LAB_DAO = new LabDAO();
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
        PrintWriter printWriter = resp.getWriter();
        Gson gson = new Gson();

        try {
            printWriter.println(gson.toJson(LAB_DAO.getLabById(
                    Integer.parseInt(req.getPathInfo().split("/")[1])).orElse(null)));
            resp.setStatus(200);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            resp.setStatus(400);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
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
