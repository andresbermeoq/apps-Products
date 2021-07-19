package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Factura_Cabecera" database table.
 * 
 */
@Entity
@Table(name="\"Factura_Cabecera\"")
@NamedQuery(name="Factura_Cabecera.findAll", query="SELECT f FROM Factura_Cabecera f")
public class Factura_Cabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="\"IVA\"")
	private BigDecimal iva;

	@Column(name="\"Subtotal\"")
	private BigDecimal subtotal;

	@Column(name="\"Total\"")
	private BigDecimal total;

	//bi-directional many-to-one association to Factura_Detalle
	@OneToMany(mappedBy="facturaCabecera")
	private List<Factura_Detalle> facturaDetalles;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Factura_Cabecera() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Factura_Detalle> getFacturaDetalles() {
		return this.facturaDetalles;
	}

	public void setFacturaDetalles(List<Factura_Detalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public Factura_Detalle addFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().add(facturaDetalle);
		facturaDetalle.setFacturaCabecera(this);

		return facturaDetalle;
	}

	public Factura_Detalle removeFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().remove(facturaDetalle);
		facturaDetalle.setFacturaCabecera(null);

		return facturaDetalle;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}