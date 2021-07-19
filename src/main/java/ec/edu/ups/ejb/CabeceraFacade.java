package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Factura_Cabecera;

@Stateless
public class CabeceraFacade extends AbstractFacade<Factura_Cabecera> {
	
	

	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;
	
	public CabeceraFacade() {
		super(Factura_Cabecera.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
