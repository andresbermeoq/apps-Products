package ec.edu.ups.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidad.Bodega;
import ec.edu.ups.entidad.Producto;


@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idBodega;
	
	@EJB
	private ProductoFacade productoFacade;
	
	@EJB
	private BodegaFacade bodegaFacade;
	
	private String categoryProduct;
	private String name;
	private BigDecimal price;
	private Integer stock;
	private Bodega bodega;
	
	private List<Bodega> bodegas;
	private List<Producto> productos;
	
	@PostConstruct
	public void init() {
		setBodegas(bodegaFacade.findAll());
		setProductos(productoFacade.findAll());
	}
		
	public ProductoFacade getProductoFacade() {
		return productoFacade;
	}
	public void setProductoFacade(ProductoFacade productoFacade) {
		this.productoFacade = productoFacade;
	}
	public String getCategoryProduct() {
		return categoryProduct;
	}
	public void setCategoryProduct(String categoryProduct) {
		this.categoryProduct = categoryProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Bodega getBodega() {
		return bodega;
	}
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
	public List<Bodega> getBodegas() {
		return bodegas;
	}
	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}
	public String getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(String idBodega) {
		this.idBodega = idBodega;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addProduct() {
		bodega = bodegaFacade.find(Integer.valueOf(idBodega));
		productoFacade.create(new Producto(categoryProduct, name, price, stock, bodega));
		setProductos(productoFacade.findAll());
	}
	
	public void onRowEdit(Producto productoUpdate) {
		productoFacade.edit(productoUpdate);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se Actualizo el Stock"));
	}
}
