package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Pedidos_Cabecera" database table.
 * 
 */
@Entity
@Table(name="\"Pedidos_Cabecera\"")
@NamedQuery(name="Pedidos_Cabecera.findAll", query="SELECT p FROM Pedidos_Cabecera p")
public class Pedidos_Cabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="\"Estado\"")
	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Fecha\"")
	private Date fecha;

	@Column(name="\"IVA\"")
	private BigDecimal iva;

	@Column(name="\"Subtotal\"")
	private BigDecimal subtotal;

	@Column(name="\"Total\"")
	private BigDecimal total;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Usuario\"")
	private Usuario usuario;

	//bi-directional many-to-one association to Pedidos_Detalle
	@OneToMany(mappedBy="pedidosCabecera")
	private List<Pedidos_Detalle> pedidosDetalles;

	public Pedidos_Cabecera() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pedidos_Detalle> getPedidosDetalles() {
		return this.pedidosDetalles;
	}

	public void setPedidosDetalles(List<Pedidos_Detalle> pedidosDetalles) {
		this.pedidosDetalles = pedidosDetalles;
	}

	public Pedidos_Detalle addPedidosDetalle(Pedidos_Detalle pedidosDetalle) {
		getPedidosDetalles().add(pedidosDetalle);
		pedidosDetalle.setPedidosCabecera(this);

		return pedidosDetalle;
	}

	public Pedidos_Detalle removePedidosDetalle(Pedidos_Detalle pedidosDetalle) {
		getPedidosDetalles().remove(pedidosDetalle);
		pedidosDetalle.setPedidosCabecera(null);

		return pedidosDetalle;
	}

}