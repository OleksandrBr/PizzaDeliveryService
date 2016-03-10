package com.rd.pizzadeliveryservice.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AccumulativeCard implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name="id_address", nullable=true)
	private Address address;
	@Column(name="acc_sum")
	private Long accumulativeSum;
	
	public AccumulativeCard() {
	}

	public AccumulativeCard(Long id, Address address, Long accumulativeSum) {
		this.id = id;
		this.address = address;
		this.accumulativeSum = accumulativeSum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getAccumulativeSum() {
		return accumulativeSum;
	}

	public void setAccumulativeSum(Long accumulativeSum) {
		this.accumulativeSum = accumulativeSum;
	}
}
