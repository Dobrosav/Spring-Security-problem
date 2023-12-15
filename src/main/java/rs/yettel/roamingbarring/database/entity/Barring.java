package rs.yettel.roamingbarring.database.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "barrings")
public class Barring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "msisdn")
    private String msisdn;
    @Column(name = "barring_type")
    private String barringType;
    @Column(name = "request_date")
    private Date requetDate;
    @Column(name = "bms_request_id")
    private String requestId;
    @Column(name = "action")
    private String action;

    @Column(name = "provisioned_date")
    private Date provisionedDate;
    public Barring() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getBarringType() {
        return barringType;
    }

    public void setBarringType(String barringType) {
        this.barringType = barringType;
    }

    public Date getRequetDate() {
        return requetDate;
    }

    public void setRequetDate(Date requetDate) {
        this.requetDate = requetDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getProvisionedDate() {
        return provisionedDate;
    }

    public void setProvisionedDate(Date provisionedDate) {
        this.provisionedDate = provisionedDate;
    }

    @Override
    public String toString() {
        return "Barring{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", barringType='" + barringType + '\'' +
                ", requetDate=" + requetDate +
                ", requestId='" + requestId + '\'' +
                ", action='" + action + '\'' +
                ", provisionedDate=" + provisionedDate +
                '}';
    }
}
