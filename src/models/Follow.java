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

@Table(name = "follows")
@NamedQueries({


    @NamedQuery(
            name = "getMyFollowAllReports",
            query = "SELECT r FROM Report AS r, Follow AS f WHERE r.employee = f.followee AND f.follower = :employee ORDER BY r.id DESC"
        ),
    @NamedQuery(
            name = "getMyFollowAllReportsCount",
            query = "SELECT COUNT(r) FROM Report AS r, Follow AS f WHERE r.employee = f.followee AND f.follower = :employee"
        ),
    @NamedQuery(
            name = "checkMyFollowReportsCount",
            query = "SELECT COUNT(f) FROM Follow AS f WHERE f.followee = :followee AND f.follower = :follower"
        ),
    @NamedQuery(
            name = "checkMyFollowReports",
            query = "SELECT f FROM Follow AS f WHERE f.followee = :followee AND f.follower = :follower"
        ),
    @NamedQuery(
            name = "getMyFollowerAllEmployees",
            query = "SELECT e FROM Employee AS e, Follow AS f WHERE f.followee = :employee AND e = f.follower ORDER BY e.id DESC"
        ),
    @NamedQuery(
            name = "getMyFolloweeAllEmployees",
            query = "SELECT e FROM Employee AS e, Follow AS f WHERE f.follower = :employee AND e = f.followee ORDER BY e.id DESC"
        )

})
@Entity
public class Follow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "followee_id", nullable = false)
    private Employee followee;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private Employee follower;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getFollowee() {
        return followee;
    }

    public void setFollowee(Employee followee) {
        this.followee = followee;
    }

    public Employee getFollower() {
        return follower;
    }

    public void setFollower(Employee follower) {
        this.follower = follower;
    }






}