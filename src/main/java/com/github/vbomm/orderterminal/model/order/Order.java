package com.github.vbomm.orderterminal.model.order;

import com.github.vbomm.orderterminal.model.orderedItem.OrderedItem;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "order_date_time")
    @Temporal(TemporalType.DATE) //Timestamp
    private Date orderDateTime;
    @Column(name = "status")
    private byte status;

    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;

    public Order() {}

    public Order(String sessionId, byte status) {
        this.sessionId = sessionId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
