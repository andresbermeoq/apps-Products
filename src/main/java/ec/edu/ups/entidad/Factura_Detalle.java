package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Factura_Detalle" database table.
 * 
 */
@Entity
@Table(name="\"Factura_Detalle\"")
@NamedQuery(name="Factura_Detalle.findAll", query="SELECT f FROM Factura_Detalle f")
public class Factura_Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Cabecera_Factura
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Cabecera_Productos\"")
	private Cabecera_Factura cabeceraFactura;

	//bi-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Productos\"")
	private Producto producto;

	public Factura_Detalle() {
	}

	public Factura_Detalle(Cabecera_Factura cabeceraFactura, Producto producto) {
		super();
		this.cabeceraFactura = cabeceraFactura;
		this.producto = producto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cabecera_Factura getCabeceraFactura() {
		return this.cabeceraFactura;
	}

	public void setCabeceraFactura(Cabecera_Factura cabeceraFactura) {
		this.cabeceraFactura = cabeceraFactura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}