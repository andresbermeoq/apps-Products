package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
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
	@SequenceGenerator(name="FACTURA_CABECERA_ID_GENERATOR", sequenceName="FACTURA_CABECERA_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTURA_CABECERA_ID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Factura_Detalle
	@OneToMany(mappedBy="facturaCabecera")
	private List<Factura_Detalle> facturaDetalles;

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
		facturaDetalle.setFacturaCabecera(this);

		return facturaDetalle;
	}

	public Factura_Detalle removeFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().remove(facturaDetalle);
		facturaDetalle.setFacturaCabecera(null);

		return facturaDetalle;
	}

}