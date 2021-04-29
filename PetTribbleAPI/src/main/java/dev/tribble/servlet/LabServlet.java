package dev.tribble.servlet;

import com.google.gson.Gson;
import dev.tribble.database.LabDAO;
import dev.tribble.model.Lab;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        try {
            String body = IOUtils.toString(req.getReader());
            Lab lab = new Gson().fromJson(body, Lab.class);
            LAB_DAO.saveLab(lab);
            resp.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500);

        } catch (Exception e) {
            resp.setStatus(400);
        }
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
        try {
            String body = IOUtils.toString(req.getReader());
            Gson gson = new Gson();
            PrintWriter printWriter = resp.getWriter();
            Lab lab = gson.fromJson(body, Lab.class);
            printWriter.println(gson.toJson(LAB_DAO.updateLab(lab)));
            resp.setStatus(200);

        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(500);

        } catch (Exception e) {
            resp.setStatus(400);
        }
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

        try {
            LAB_DAO.deleteLabById(Integer.parseInt(req.getPathInfo().split("/")[1]));
            resp.setStatus(200);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            resp.setStatus(400);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
