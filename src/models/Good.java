package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "Good")
@NamedQueries({


    @NamedQuery(
            name = "getMyGoodAllReports",
            query = "SELECT r FROM Report AS r , Good AS g WHERE g.goodemployee = :employee AND g.goodreport = r ORDER BY r.id DESC"
        ),
    @NamedQuery(
            name = "getMyGooderAllReports",
            query = "SELECT r FROM Report AS r , Good AS g WHERE g.goodreport.employee = :employee AND g.goodreport = r  ORDER BY r.id DESC"
        ),
    @NamedQuery(
            name = "getGoodAllEmployees",
            query = "SELECT e FROM Employee AS e , Good AS g WHERE g.goodreport = :report AND g.goodemployee = e ORDER BY e.id DESC"
        ),

    @NamedQuery(
            name = "checkGoodReports",
            query = "SELECT g FROM Good AS g WHERE g.goodemployee = :employee AND g.goodreport = :report"
        ),

    @NamedQuery(
            name = "checkGoodReportsCount",
            query = "SELECT COUNT(g) FROM Good AS g WHERE g.goodemployee = :employee AND g.goodreport = :report"
        )


})
@Entity
public class Good {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "goodemployee_id", nullable = false)
    private Employee goodemployee;

    @ManyToOne
    @JoinColumn(name = "goodreport_id", nullable = false)
    private Report goodreport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getGoodemployee() {
        return goodemployee;
    }

    public void setGoodemployee(Employee goodemployee) {
        this.goodemployee = goodemployee;
    }

    public Report getGoodreport() {
        return goodreport;
    }

    public void setGoodreport(Report goodreport) {
        this.goodreport = goodreport;
    }








}