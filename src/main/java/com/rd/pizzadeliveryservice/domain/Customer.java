package com.rd.pizzadeliveryservice.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToOne
	@JoinColumn(name="id_acc_card", nullable=true)
	private AccumulativeCard accumulativeCard;
	
	public Customer(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}
	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

}
