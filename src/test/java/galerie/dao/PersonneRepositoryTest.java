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
import galerie.entity.Tableau;
import galerie.entity.Transaction;
import java.util.ArrayList;
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
    
    
    public void budgetArt(){
        Personne pers =new Personne();
        Tableau tab1= new Tableau();
        Transaction transac1 = new Transaction();
        transac1.setOeuvre(tab1);
        transac1.setPrixVente(10);
        Tableau tab2= new Tableau();
        Transaction transac2 = new Transaction();
        transac2.setOeuvre(tab2);
        transac2.setPrixVente(10);
        Date date= new Date(2021, 01, 20);
        Date date1= new Date(2020, 01, 20);
        transac1.setVenduLe(date);
        transac2.setVenduLe(date1);
        transac1.setClient(pers);
        transac2.setClient(pers);
        
        
        assertEquals(2, pers.budgetArt(2021), "On doit trouver 10" );
    }
     

}
