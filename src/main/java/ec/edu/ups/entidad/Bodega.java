package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Bodega" database table.
 * 
 */
@Entity
@Table(name="\"Bodega\"")
@NamedQuery(name="Bodega.findAll", query="SELECT b FROM Bodega b")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private String name;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="bodega")
	private List<Producto> productos;

	public Bodega() {
	}
	
	public Bodega(String address, String name) {
		super();
		this.address = address;
		this.name = name;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setBodega(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setBodega(null);

		return producto;
	}

}