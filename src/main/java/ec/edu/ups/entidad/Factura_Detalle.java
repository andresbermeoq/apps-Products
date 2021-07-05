package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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
	@SequenceGenerator(name="FACTURA_DETALLE_ID_GENERATOR", sequenceName="FACTURA_DETALLE_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FACTURA_DETALLE_ID_GENERATOR")
	private Integer id;

	@Column(name="value_iva")
	private BigDecimal valueIva;

	@Column(name="value_total")
	private BigDecimal valueTotal;

	@Column(name="value_unitary")
	private BigDecimal valueUnitary;

	//bi-directional many-to-one association to Factura_Cabecera
	@ManyToOne
	@JoinColumn(name="id_cabecera")
	private Factura_Cabecera facturaCabecera;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public Factura_Detalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValueIva() {
		return this.valueIva;
	}

	public void setValueIva(BigDecimal valueIva) {
		this.valueIva = valueIva;
	}

	public BigDecimal getValueTotal() {
		return this.valueTotal;
	}

	public void setValueTotal(BigDecimal valueTotal) {
		this.valueTotal = valueTotal;
	}

	public BigDecimal getValueUnitary() {
		return this.valueUnitary;
	}

	public void setValueUnitary(BigDecimal valueUnitary) {
		this.valueUnitary = valueUnitary;
	}

	public Factura_Cabecera getFacturaCabecera() {
		return this.facturaCabecera;
	}

	public void setFacturaCabecera(Factura_Cabecera facturaCabecera) {
		this.facturaCabecera = facturaCabecera;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}