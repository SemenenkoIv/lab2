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
@Table(name = "reestrcard", catalog = "privatehospital", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reestrcard.findAll", query = "SELECT r FROM Reestrcard r"),
    @NamedQuery(name = "Reestrcard.findByIdCard", query = "SELECT r FROM Reestrcard r WHERE r.idCard = :idCard")})
public class Reestrcard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_card")
    private Integer idCard;
    @Lob
    @Size(max = 65535)
    @Column(name = "namepacient")
    private String namepacient;

    public Reestrcard() {
    }

    public Reestrcard(Integer idCard) {
        this.idCard = idCard;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public void setIdCard(Integer idCard) {
        this.idCard = idCard;
    }

    public String getNamepacient() {
        return namepacient;
    }

    public void setNamepacient(String namepacient) {
        this.namepacient = namepacient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCard != null ? idCard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reestrcard)) {
            return false;
        }
        Reestrcard other = (Reestrcard) object;
        if ((this.idCard == null && other.idCard != null) || (this.idCard != null && !this.idCard.equals(other.idCard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "julia.entity.Reestrcard[ idCard=" + idCard + " ]";
    }
    
}
