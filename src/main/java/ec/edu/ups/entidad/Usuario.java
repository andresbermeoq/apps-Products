package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Usuario" database table.
 * 
 */
@Entity
@Table(name="\"Usuario\"")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private String email;

	@Column(name="\"idCard\"")
	private String idCard;

	@Column(name="last_name")
	private String lastName;

	private String name;

	private String role;

	//bi-directional many-to-one association to Cabecera_Factura
	@OneToMany(mappedBy="usuario")
	private List<Cabecera_Factura> cabeceraFacturas;

	//bi-directional many-to-one association to Pedidos_Cabecera
	@OneToMany(mappedBy="usuario")
	private List<Pedidos_Cabecera> pedidosCabeceras;

	public Usuario() {
	}

	public Usuario(String address, String email, String idCard, String lastName, String name, String role) {
		super();
		this.address = address;
		this.email = email;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Cabecera_Factura> getCabeceraFacturas() {
		return this.cabeceraFacturas;
	}

	public void setCabeceraFacturas(List<Cabecera_Factura> cabeceraFacturas) {
		this.cabeceraFacturas = cabeceraFacturas;
	}

	public Cabecera_Factura addCabeceraFactura(Cabecera_Factura cabeceraFactura) {
		getCabeceraFacturas().add(cabeceraFactura);
		cabeceraFactura.setUsuario(this);

		return cabeceraFactura;
	}

	public Cabecera_Factura removeCabeceraFactura(Cabecera_Factura cabeceraFactura) {
		getCabeceraFacturas().remove(cabeceraFactura);
		cabeceraFactura.setUsuario(null);

		return cabeceraFactura;
	}

	public List<Pedidos_Cabecera> getPedidosCabeceras() {
		return this.pedidosCabeceras;
	}

	public void setPedidosCabeceras(List<Pedidos_Cabecera> pedidosCabeceras) {
		this.pedidosCabeceras = pedidosCabeceras;
	}

	public Pedidos_Cabecera addPedidosCabecera(Pedidos_Cabecera pedidosCabecera) {
		getPedidosCabeceras().add(pedidosCabecera);
		pedidosCabecera.setUsuario(this);

		return pedidosCabecera;
	}

	public Pedidos_Cabecera removePedidosCabecera(Pedidos_Cabecera pedidosCabecera) {
		getPedidosCabeceras().remove(pedidosCabecera);
		pedidosCabecera.setUsuario(null);

		return pedidosCabecera;
	}

}