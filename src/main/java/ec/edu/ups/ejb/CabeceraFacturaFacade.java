package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Cabecera_Factura;

@Stateless
public class CabeceraFacturaFacade extends AbstractFacade<Cabecera_Factura> {
	

	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;
	
	public CabeceraFacturaFacade() {
		super(Cabecera_Factura.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
