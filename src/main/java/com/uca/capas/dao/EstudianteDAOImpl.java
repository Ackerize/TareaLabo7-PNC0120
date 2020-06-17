package com.uca.capas.dao;

import com.uca.capas.domain.Estudiante;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EstudianteDAOImpl implements EstudianteDAO {

    @PersistenceContext(unitName = "capas")
    private EntityManager entityManager;
    
    @Override
    public List<Estudiante> findAll() throws DataAccessException {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from public.estudiante");
        Query query = entityManager.createNativeQuery(sb.toString(), Estudiante.class);
        List<Estudiante> estudiantes = query.getResultList();

        return estudiantes;
    }
    
    @Override
    @Transactional
    public void insert(Estudiante estudiante) throws DataAccessException {
        try {
        	if(estudiante.getCodigo() == null) {
        		entityManager.persist(estudiante);
        	}else {
        		entityManager.merge(estudiante);
        		entityManager.flush();
        	}
        }catch(Throwable e) {
        	e.printStackTrace();
        }
    }

	@Override
	@Transactional
	public void delete(Integer codigoEstudiante) throws DataAccessException {
		Estudiante estudiante = entityManager.find(Estudiante.class, codigoEstudiante);
		entityManager.remove(estudiante);
		
	}

}
