package br.com.cinq.spring.data.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="CITY", schema="SAMPLE")
@Entity
public class City {
	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID", referencedColumnName="ID")
	private Country country;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	

}
