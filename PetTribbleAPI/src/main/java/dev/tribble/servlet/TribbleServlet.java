package dev.tribble.servlet;

import com.google.gson.Gson;
import dev.tribble.database.TribbleDAO;
import dev.tribble.model.Tribble;
import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
        PrintWriter printWriter = resp.getWriter();
        Gson gson = new Gson();

        //if path parameter present ie /tribble/{id}
        if(req.getPathInfo() != null) {
            try {
                printWriter.println(gson.toJson(TRIBBLE_DAO.getTribbleById(
                        Integer.parseInt(req.getPathInfo().split("/")[1])).orElse(null)));
                resp.setStatus(200);

            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                e.printStackTrace();
                resp.setStatus(400);

            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(500);
            }

            //if no path parameter present ie /tribble
        } else {
            try {
                printWriter.println(gson.toJson(TRIBBLE_DAO.getAll()));
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
            Tribble tribble = new Gson().fromJson(body, Tribble.class);
            TRIBBLE_DAO.saveTribble(tribble);
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
            Tribble tribble = gson.fromJson(body, Tribble.class);
            printWriter.println(gson.toJson(TRIBBLE_DAO.updateTribble(tribble)));
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
            TRIBBLE_DAO.deleteTribbleById(Integer.parseInt(req.getPathInfo().split("/")[1]));
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
