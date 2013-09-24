package com.gft.testingtraining.arquillian.ejb;


import javax.ejb.*;
import javax.persistence.*;
import com.gft.testingtraining.arquillian.domain.*;

/** 
 * Small example of EJB 3.1, with DB access
 */
@Stateless
public class GreeterFromDatabase {
    
    @PersistenceContext
    EntityManager em;
    
    public BasicModelUnit greet(Long id) {
        return em.find(BasicModelUnit.class, id);
    }

    public void save(String text) {
        BasicModelUnit message = new BasicModelUnit();
        message.setText(text);
        em.persist(message);
    }
}
