/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia.servlets;

import julia.dao.DB;
import julia.dao.ReestrDAO;
import julia.dao.VisitDAO;
import julia.entity.Reestrcard;
import julia.entity.Visits;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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
        out.println("<title>UpdateServlet</title>");            
        out.println("</head>");
        out.println("<body>");  
        out.println("Select rows to update<br><br>");
        out.println("<form action='UpdateServlet' method='post'>");
        out.println("<table border='1px' style='margin-left: 10px; border-spacing: 0px;'>");
        
        
        String dbTable = (String) request.getSession().getAttribute("dbTable");
        switch (dbTable){
            case "visits":
                
                 
                out.println("<tr>");
                out.println("<td>id</td>");
                out.println("<td><input type='text' name='id_v'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>name</td>"); 
                out.println("<td><input type='text' name='name_d'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>count</td>"); 
                out.println("<td><input type='text' name='record'></td>");
                out.println("</tr>");
                 
                break;
            case "reestrcard":                
                out.println("<tr>");
                out.println("<td>Type id</td>"); 
                out.println("<td><input type='text' name='id_card'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Type name</td>");
                out.println("<td><input type='text' name='namepacient'></td>");
                out.println("</tr>");
                break;
        }
        out.println("</table>");
        out.println("<br><input type='submit' name='update' value='update' style='width: 80px'>");
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
        int id_v = Integer.parseInt(request.getParameter("id"));
        int id_card = Integer.parseInt(request.getParameter("id"));
        
        switch (dbTable){
            case "visits":
                VisitDAO vis = new VisitDAO();
                Visits m = new Visits();
                m.setNameD(request.getParameter("name_d"));
                m.setRecord(request.getParameter("record"));           
                 
                vis.update(id_v, m);
                break;
            case "reestrcard":
                ReestrDAO type = new ReestrDAO();
                Reestrcard c = new Reestrcard();
                c.setNamepacient(request.getParameter("namepacient"));
                type.update(id_card, c);
                break;
        }
        out.println("UPDATE executed successfully!");
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
