package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the "Productos" database table.
 * 
 */
@Entity
@Table(name="\"Productos\"")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="category_product")
	private String categoryProduct;

	@Column(name="id_bodega")
	private Integer idBodega;

	private String name;

	private BigDecimal price;

	private Integer stock;

	//bi-directional many-to-one association to Factura_Detalle
	@OneToMany(mappedBy="producto")
	private List<Factura_Detalle> facturaDetalles;

	public Producto() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryProduct() {
		return this.categoryProduct;
	}

	public void setCategoryProduct(String categoryProduct) {
		this.categoryProduct = categoryProduct;
	}

	public Integer getIdBodega() {
		return this.idBodega;
	}

	public void setIdBodega(Integer idBodega) {
		this.idBodega = idBodega;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Factura_Detalle> getFacturaDetalles() {
		return this.facturaDetalles;
	}

	public void setFacturaDetalles(List<Factura_Detalle> facturaDetalles) {
		this.facturaDetalles = facturaDetalles;
	}

	public Factura_Detalle addFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().add(facturaDetalle);
		facturaDetalle.setProducto(this);

		return facturaDetalle;
	}

	public Factura_Detalle removeFacturaDetalle(Factura_Detalle facturaDetalle) {
		getFacturaDetalles().remove(facturaDetalle);
		facturaDetalle.setProducto(null);

		return facturaDetalle;
	}

}