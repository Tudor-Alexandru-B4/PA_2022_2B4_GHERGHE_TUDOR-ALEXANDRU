package others;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class PersistenceService {
    @PersistenceUnit(name="somePU")
    EntityManagerFactory emf;

    public void persistenceMethod(Entity myEntity){
        EntityManager em = emf.createEntityManager();

        em.close();
    }

}
