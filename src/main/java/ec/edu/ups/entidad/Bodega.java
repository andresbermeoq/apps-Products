package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Bodegas" database table.
 * 
 */
@Entity
@Table(name="\"Bodegas\"")
@NamedQuery(name="Bodega.findAll", query="SELECT b FROM Bodega b")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private String name;

	public Bodega() {
	}
	
	public Bodega(String address, String name) {
		super();
		this.address = address;
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}