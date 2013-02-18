package org.agric.oxm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "message")
public class Message extends BaseData {

    private User sender;

    private User to;

    private String message;

    private Date datePosted;

    private boolean senderCleared = false;

    private boolean toCleared = false;

    private boolean isSeen = false;

    public Message() {
	super();
    }

    @ManyToOne
    @JoinColumn(name = "sender", nullable = true)
    public User getSender() {
	return sender;
    }

    public void setSender(User sender) {
	this.sender = sender;
    }

    @ManyToOne
    @JoinColumn(name = "to", nullable = true)
    public User getTo() {
	return to;
    }

    public void setTo(User to) {
	this.to = to;
    }

    @Column(name = "message", nullable = false, length = 1000)
    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_posted", nullable = false)
    public Date getDatePosted() {
	return datePosted;
    }

    public void setDatePosted(Date datePosted) {
	this.datePosted = datePosted;
    }

    @Type(type = "true_false")
    @Column(name = "is_sender_cleared", nullable = true)
    public boolean isSenderCleared() {
	return senderCleared;
    }

    public void setSenderCleared(boolean senderCleared) {
	this.senderCleared = senderCleared;
    }

    @Type(type = "true_false")
    @Column(name = "is_to_cleared", nullable = true)
    public boolean isToCleared() {
	return toCleared;
    }

    public void setToCleared(boolean toCleared) {
	this.toCleared = toCleared;
    }

    @Type(type = "true_false")
    @Column(name = "is_seen", nullable = true)
    public boolean isSeen() {
	return isSeen;
    }

    public void setSeen(boolean isSeen) {
	this.isSeen = isSeen;
    }

}