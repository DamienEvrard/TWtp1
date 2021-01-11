package galerie.dao;

import galerie.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import galerie.dao.PersonneRepository;
import galerie.entity.Exposition;
import galerie.entity.Galerie;
import java.util.Date;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class PersonneRepositoryTest {

    @Autowired
    private PersonneRepository personneDAO;
    
    private Personne personne, personne1;

    @Test
    @Sql("test-data_personne.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Personne'");
        int combienDansLeJeuDeTest = 1; 
        long nombre = personneDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 personne" );
    }
    
    @Test
    @Sql("test-data_personne.sql")
    public void insertPersonne(){
        personne=new Personne("nom", "adresse");
        personneDAO.save(personne);
        long nombre = personneDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 personnes" );
    }
    
    
    @Test
    @Sql("test-data_personne.sql")
    public void deletePersonne(){
        personne=new Personne("nom", "adresse");
        personne1=new Personne("nom1", "adresse1");
        personneDAO.save(personne);
        personneDAO.save(personne1);
        personneDAO.delete(personne);
        long nombre = personneDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 personnes" );
    }

}
