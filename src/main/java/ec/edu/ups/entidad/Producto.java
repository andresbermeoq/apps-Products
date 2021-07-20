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

	@Column(name="\"categoryProduct\"")
	private String categoryProduct;

	private String name;

	private BigDecimal price;

	private Integer stock;

	//bi-directional many-to-one association to Factura_Detalle
	@OneToMany(mappedBy="producto")
	private List<Factura_Detalle> facturaDetalles;

	//bi-directional many-to-one association to Pedidos_Detalle
	@OneToMany(mappedBy="producto")
	private List<Pedidos_Detalle> pedidosDetalles;

	//bi-directional many-to-one association to Bodega
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Bodega\"")
	private Bodega bodega;

	public Producto() {
	}

	public Producto(String categoryProduct, String name, BigDecimal price, Integer stock, Bodega bodega) {
		super();
		this.categoryProduct = categoryProduct;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.bodega = bodega;
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

	public List<Pedidos_Detalle> getPedidosDetalles() {
		return this.pedidosDetalles;
	}

	public void setPedidosDetalles(List<Pedidos_Detalle> pedidosDetalles) {
		this.pedidosDetalles = pedidosDetalles;
	}

	public Pedidos_Detalle addPedidosDetalle(Pedidos_Detalle pedidosDetalle) {
		getPedidosDetalles().add(pedidosDetalle);
		pedidosDetalle.setProducto(this);

		return pedidosDetalle;
	}

	public Pedidos_Detalle removePedidosDetalle(Pedidos_Detalle pedidosDetalle) {
		getPedidosDetalles().remove(pedidosDetalle);
		pedidosDetalle.setProducto(null);

		return pedidosDetalle;
	}

	public Bodega getBodega() {
		return this.bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", categoryProduct=" + categoryProduct + ", name=" + name + ", price=" + price
				+ ", stock=" + stock + ", facturaDetalles=" + facturaDetalles + ", pedidosDetalles=" + pedidosDetalles
				+ ", bodega=" + bodega + "]";
	}
}