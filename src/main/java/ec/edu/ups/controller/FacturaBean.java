package ec.edu.ups.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.DetalleFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entidad.Producto;
import ec.edu.ups.entidad.Usuario;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class FacturaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date fecha;
	
	private String idUser;
	private Integer idProduct;
	
	@EJB
	private UserFacade userFacade;
	
	private Usuario usuario;

	private List<Usuario> usuarios;
	
	
	@EJB
	private ProductoFacade productoFacade;
	
	private Producto producto;
	private Producto productoList;
	private List<Producto> productos;
	private List<Producto> productosDetalles;
	
	@EJB
	private DetalleFacade detalleFacade;
	
	private BigDecimal valueIva;
	private BigDecimal valueTotal;
	private BigDecimal ValueUnitary;
	
	
	@PostConstruct
	public void init() {
		setUsuarios(userFacade.findAll());
		setProductos(productoFacade.findAll());
		productosDetalles = new ArrayList<Producto>();
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public BigDecimal getValueIva() {
		return valueIva;
	}

	public void setValueIva(BigDecimal valueIva) {
		this.valueIva = valueIva;
	}

	public BigDecimal getValueTotal() {
		return valueTotal;
	}

	public void setValueTotal(BigDecimal valueTotal) {
		this.valueTotal = valueTotal;
	}

	public BigDecimal getValueUnitary() {
		return ValueUnitary;
	}

	public void setValueUnitary(BigDecimal valueUnitary) {
		ValueUnitary = valueUnitary;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Producto getProductoList() {
		return productoList;
	}

	public void setProductoList(Producto productoList) {
		this.productoList = productoList;
	}
	
	public List<Producto> getProductosDetalles() {
		return productosDetalles;
	}

	public void setProductosDetalles(List<Producto> productosDetalles) {
		this.productosDetalles = productosDetalles;
	}
	
	public void searchProduct() {
		productoList = productoFacade.find(idProduct);
		productosDetalles.add(productoList);
	}
	
}
