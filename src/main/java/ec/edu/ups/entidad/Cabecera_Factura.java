package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Cabecera_Factura" database table.
 * 
 */
@Entity
@Table(name="\"Cabecera_Factura\"")
@NamedQuery(name="Cabecera_Factura.findAll", query="SELECT c FROM Cabecera_Factura c")
public class Cabecera_Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

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
	@JoinColumn(name="\"idUsuario\"")
	private Usuario usuario;

	//bi-directional many-to-one association to Factura_Detalle
	@OneToMany(mappedBy="cabeceraFactura")
	private List<Factura_Detalle> facturaDetalles;

	public Cabecera_Factura() {
	}

	public Cabecera_Factura(Date fecha, BigDecimal iva, BigDecimal subtotal, BigDecimal total, Usuario usuario,
			List<Factura_Detalle> facturaDetalles) {
		super();
		this.fecha = fecha;
		this.iva = iva;
		this.subtotal = subtotal;
		this.total = total;
		this.usuario = usuario;
		this.facturaDetalles = facturaDetalles;
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

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Factura_Detalle> getFacturaDetalles() {
		return this.facturaDetalles;
	}

	public void setFacturaDetalles(List<Factura_Detalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public Factura_Detalle addFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().add(facturaDetalle);
		facturaDetalle.setCabeceraFactura(this);

		return facturaDetalle;
	}

	public Factura_Detalle removeFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().remove(facturaDetalle);
		facturaDetalle.setCabeceraFactura(null);

		return facturaDetalle;
	}

}