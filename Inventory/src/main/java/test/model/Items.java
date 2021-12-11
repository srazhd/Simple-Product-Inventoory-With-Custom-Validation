package test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

import annotations.ItemName;
import annotations.ModelName;

@Entity
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Brand brand;
	
	@OneToOne
	private Models models;
	
	@ItemName
	@NotEmpty(message = "Item name is mandatory")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="entry_date" , length = 255)
	private Date entryDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Models getModels() {
		return models;
	}

	public void setModels(Models models) {
		this.models = models;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
