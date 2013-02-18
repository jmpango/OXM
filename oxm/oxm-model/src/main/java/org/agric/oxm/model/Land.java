package org.agric.oxm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "land")
public class Land extends BaseData {

    private Producer producer;

    private Crop crop;

    private int size;

    public Land() {
	super();
    }

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    public Producer getProducer() {
	return producer;
    }

    public void setProducer(Producer producer) {
	this.producer = producer;
    }

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    public Crop getCrop() {
	return crop;
    }

    public void setCrop(Crop crop) {
	this.crop = crop;
    }

    @Column(name = "size", nullable = false)
    public int getSize() {
	return size;
    }

    public void setSize(int size) {
	this.size = size;
    }

}