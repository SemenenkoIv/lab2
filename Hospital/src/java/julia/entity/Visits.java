/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package julia.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hanna
 */
@Entity
@Table(name = "visits", catalog = "privatehospital", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visits.findAll", query = "SELECT v FROM Visits v"),
    @NamedQuery(name = "Visits.findByIdV", query = "SELECT v FROM Visits v WHERE v.idV = :idV")})
public class Visits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_v")
    private Integer idV;
    @Lob
    @Size(max = 65535)
    @Column(name = "name_d")
    private String nameD;
    @Lob
    @Size(max = 65535)
    @Column(name = "record")
    private String record;

    public Visits() {
    }

    public Visits(Integer idV) {
        this.idV = idV;
    }

    public Integer getIdV() {
        return idV;
    }

    public void setIdV(Integer idV) {
        this.idV = idV;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idV != null ? idV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visits)) {
            return false;
        }
        Visits other = (Visits) object;
        if ((this.idV == null && other.idV != null) || (this.idV != null && !this.idV.equals(other.idV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "julia.entity.Visits[ idV=" + idV + " ]";
    }
    
}
