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
import static julia.dao.DB.*;
import julia.entity.Reestrcard;

/**
 *
 * @author Hanna
 */
public class ReestrDAO implements DAO<Reestrcard> {

    String dbtname = "reestrcard";

    @Override
    public ArrayList<Reestrcard> select() {
        ArrayList<Reestrcard> result = new ArrayList<>();
        int i = 0;
        try {
            resSet = statmt.executeQuery("SELECT * FROM " + dbtname);
            while (resSet.next()) {
                result.add(new Reestrcard());
                result.get(i).setIdCard(resSet.getInt("id_card"));
                result.get(i).setNamepacient(resSet.getString("namepacient"));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReestrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public void insert(Reestrcard t) {
        try {
            resSet = statmt.executeQuery(
                    "select max(id_card) from " + dbtname
            );
            resSet.next();
            t.setIdCard(resSet.getInt(1) + 1);
            statmt.execute(
                    "INSERT INTO " + dbtname + " VALUES("
                    + t.getIdCard() + ",\""
                    + t.getNamepacient()
                    + "\")"
            );
        } catch (SQLException ex) {
            Logger.getLogger(ReestrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            statmt.execute(
                    "DELETE FROM " + dbtname + " WHERE id_card = " + id
            );
        } catch (SQLException ex) {
            Logger.getLogger(ReestrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(int id, Reestrcard t) {
        try {
            statmt.execute(
                    "UPDATE "+dbtname+" SET namepacient=\"" + t.getNamepacient()
                            + "\" WHERE id_card =" + id
            );
        } catch (SQLException ex) {
            Logger.getLogger(ReestrDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
