package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Usuarios" database table.
 * 
 */
@Entity
@Table(name="\"Usuarios\"")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	private String address;

	@Column(name="id_card")
	private String idCard;

	@Column(name="last_name")
	private String lastName;

	private String name;

	private String role;

	//bi-directional many-to-one association to Factura_Cabecera
	@OneToMany(mappedBy="usuario")
	private List<Factura_Cabecera> facturaCabeceras;

	public Usuario() {
	}
	
	public Usuario(String address, String idCard, String lastName, String name, String role) {
		super();
		this.address = address;
		this.idCard = idCard;
		this.lastName = lastName;
		this.name = name;
		this.role = role;
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

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Factura_Cabecera> getFacturaCabeceras() {
		return this.facturaCabeceras;
	}

	public void setFacturaCabeceras(List<Factura_Cabecera> facturaCabeceras) {
		this.facturaCabeceras = facturaCabeceras;
	}

	public Factura_Cabecera addFacturaCabecera(Factura_Cabecera facturaCabecera) {
		getFacturaCabeceras().add(facturaCabecera);
		facturaCabecera.setUsuario(this);

		return facturaCabecera;
	}

	public Factura_Cabecera removeFacturaCabecera(Factura_Cabecera facturaCabecera) {
		getFacturaCabeceras().remove(facturaCabecera);
		facturaCabecera.setUsuario(null);

		return facturaCabecera;
	}

}