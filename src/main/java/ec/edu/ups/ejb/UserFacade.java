package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Usuario;

@Stateless
public class UserFacade extends AbstractFacade<Usuario> {
	
	@PersistenceContext(unitName = "Practice-Products")
	private EntityManager em;

	public UserFacade() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
