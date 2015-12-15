package julia.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import julia.dao.DB;
import julia.dao.ReestrDAO;
import julia.dao.VisitDAO;
import julia.entity.Reestrcard;
import julia.entity.Visits;

/**
 *
 * @author Admin
 */
@WebServlet(name = "InsertServlet", urlPatterns = {"/InsertServlet"})
public class InsertServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("windows-1251");
        try {
            DB.Connect();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SelectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>InsertServlet</title>");            
        out.println("</head>");
        out.println("<body>");  
        out.println("<form action='InsertServlet' method='post'>");
        out.println("<table border='1px' style='margin-left: 10px; border-spacing: 0px;'>");
        
        
        String dbTable = (String) request.getSession().getAttribute("dbTable");
        switch (dbTable){
            case "visits":              
            
                out.println("<tr>");
                out.println("<td>id_v</td>");
                out.println("<td><input type='text' name='id_v'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>name</td>"); 
                out.println("<td><input type='text' name='name_d'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>record</td>"); 
                out.println("<td><input type='text' name='record'></td>");
                out.println("</tr>");
                break;
            case "reestrcard":                
         
                out.println("<tr>");
                out.println("<td>Type id</td>"); 
                out.println("<td><input type='text' name='id_card'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Name Pacient</td>");
                out.println("<td><input type='text' name='namepacient'></td>");
                out.println("</tr>");
                break;
        }
        out.println("</table>");
        out.println("<br><input type='submit' name='insert' value='insert' style='width: 80px'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String dbTable = (String) request.getSession().getAttribute("dbTable");
        try {
            DB.Connect();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SelectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        switch (dbTable){
            case "visits":
                VisitDAO vis = new VisitDAO();
                Visits m = new Visits();
                m.setNameD(request.getParameter("name_d"));
                m.setRecord(request.getParameter("record"));
                vis.insert(m);
                break;
            case "reestrcard":
                ReestrDAO type = new ReestrDAO();
                Reestrcard c = new  Reestrcard();
                c.setNamepacient(request.getParameter("namepacient"));
                type.insert(c);
                break;
        }
        out.println("INSERT executed successfully!");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
