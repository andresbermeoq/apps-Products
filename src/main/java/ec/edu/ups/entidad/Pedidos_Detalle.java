package ec.edu.ups.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Pedidos_Detalles" database table.
 * 
 */
@Entity
@Table(name="\"Pedidos_Detalles\"")
@NamedQuery(name="Pedidos_Detalle.findAll", query="SELECT p FROM Pedidos_Detalle p")
public class Pedidos_Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Pedidos_Cabecera
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Cabecera\"")
	private Pedidos_Cabecera pedidosCabecera;

	//bi-directional many-to-one association to Producto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="\"id_Productos\"")
	private Producto producto;

	public Pedidos_Detalle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedidos_Cabecera getPedidosCabecera() {
		return this.pedidosCabecera;
	}

	public void setPedidosCabecera(Pedidos_Cabecera pedidosCabecera) {
		this.pedidosCabecera = pedidosCabecera;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}