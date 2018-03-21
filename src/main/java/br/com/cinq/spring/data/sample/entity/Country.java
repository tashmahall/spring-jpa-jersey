package br.com.cinq.spring.data.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="COUNTRY", schema="SAMPLE")
@Entity
@NamedQueries({
	@NamedQuery(name=Country.FIND_BY_NAME, query="select u from Country u where u.name = :countryName"),
	@NamedQuery(name=Country.FIND_LIKE_NAME, query="select u from Country u where u.name like CONCAT(:countryName,'%')"),
	@NamedQuery(name=Country.FIND_ALL, query="select u from Country u")
})
public class Country {
	public static final String FIND_BY_NAME="findByName";
	public static final String FIND_LIKE_NAME="findLikeName";
	public static final String FIND_ALL="findAll";
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Integer id;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
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
	

}
