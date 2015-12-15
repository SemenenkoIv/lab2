/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static julia.dao.DB.resSet;
import static julia.dao.DB.statmt;
import julia.entity.Visits;

/**
 *
 * @author Hanna
 */
public class VisitDAO implements DAO<Visits>{
String dbtname = "visits";
int i = 0;
    @Override
    public ArrayList<Visits> select() {
         
         
        ArrayList<Visits> result = new ArrayList<>();
    try {
        resSet = statmt.executeQuery("SELECT * FROM "+dbtname);
    } catch (SQLException ex) {
        Logger.getLogger(VisitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        while (resSet.next()){
            result.add(new Visits());
            result.get(i).setIdV(resSet.getInt("id_v"));             
            result.get(i).setNameD(resSet.getString("name_d"));
            result.get(i).setRecord(resSet.getString("record"));
            i++;
        }
    } catch (SQLException ex) {
        Logger.getLogger(VisitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
         
        
        return result;
    }

    @Override
    public void insert(Visits t) {
    try {
        resSet = statmt.executeQuery("SELECT mac(id_v) FROM " +dbtname);
           resSet.next();     
        t.setIdV(resSet.getInt(1)+1);  
        statmt.execute("INSERT INTO "+dbtname+" VALUES("+t.getIdV()+",\""+t.getNameD()+"\",\""+t.getRecord()+"\")");
    } catch (SQLException ex) {
        Logger.getLogger(VisitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
 }

    @Override
    public void delete(int id) {
    try {
        statmt.execute("DELETE FROM "+dbtname+" WHERE id_v = "+id);
    } catch (SQLException ex) {
        Logger.getLogger(VisitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
 }

    @Override
    public void update(int id, Visits t) {
    try {
        statmt.execute("UPDATE "+dbtname+" SET name_d = \""+ t.getNameD()+"\", record = \""+ t.getRecord()+"\" WHERE id_v = "+id);
    } catch (SQLException ex) {
        Logger.getLogger(VisitDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    
    public ArrayList<Visits> searchByName(String name) throws SQLException{
        ArrayList<Visits> result = new ArrayList<>();
        int i = 0;
        resSet = statmt.executeQuery(
                "SELECT * FROM "+dbtname+" WHERE name_d LIKE '%" + name + "%'"
        );
        while(resSet.next()){
            result.add(new Visits());
            result.get(i).setIdV(resSet.getInt("id_v"));
            result.get(i).setNameD(resSet.getString("name_d"));
            result.get(i).setRecord(resSet.getString("record"));
           
            i++;
        }
        return result;
    }
    
    public ArrayList<Visits> searchByCount(String record) throws SQLException{
        ArrayList<Visits> result = new ArrayList<>();
        int i = 0;
        resSet = statmt.executeQuery(
                "SELECT * FROM "+dbtname+" WHERE recotd LIKE '%"  + record+"%'"
        );
        while(resSet.next()){
            result.add(new Visits());
            result.get(i).setIdV(resSet.getInt("id_v"));
            result.get(i).setNameD(resSet.getString("name_d"));             
            result.get(i).setRecord(resSet.getString("record"));
            i++;
        }
        return result;
    }
}
