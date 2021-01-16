package galerie.dao;

import galerie.dao.*;
import galerie.entity.Exposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import galerie.dao.ExpositionRepository;
import galerie.dao.GalerieRepository;
import galerie.entity.Galerie;
import galerie.entity.Tableau;
import galerie.entity.Transaction;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class ExpositionRepositoryTest {

    @Autowired
    private ExpositionRepository expositionDAO;
    @Autowired
    private GalerieRepository galerieDAO;
    
    private Exposition exposition, exposition2;
   
    
    
    @Test
    @Sql("test-data_galerie.sql")
    @Sql("test-data_exposition.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Exposition'");
        int combienDansLeJeuDeTest = 1; 
        long nombre = expositionDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 exposition" );
    }
    
    @Test
    @Sql("test-data_galerie.sql")
    @Sql("test-data_exposition.sql")
    public void insertExposition(){
        Date date = new Date(2021,02,01);
        Galerie galerie = galerieDAO.getOne(1);
        exposition = new Exposition(date, "exposition2",120, galerie);
        expositionDAO.save(exposition);
        long nombre = expositionDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 expositions" );
    }
    
    
    @Test
    @Sql("test-data_galerie.sql")
    @Sql("test-data_exposition.sql")
    public void deleteExposition(){
        Date date = new Date(2021,02,01);
        Date date1 = new Date(2021,03,10);
        Galerie galerie = galerieDAO.getOne(1);
        exposition = new Exposition(date, "exposition2",120, galerie);
        exposition2 = new Exposition(date1, "exposition3",120, galerie);
        expositionDAO.save(exposition);
        expositionDAO.save(exposition2);
        expositionDAO.delete(exposition);
        long nombre = expositionDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 expositions" );
    }
    
    @Test
    @Sql("test-data_galerie.sql")
    @Sql("test-data_exposition.sql")
    @Sql("test-data_personne.sql")
    @Sql("test-data_artiste.sql")
    @Sql("test-data_tableau.sql")
    @Sql("test-data_transaction.sql")
    public void chiffreAffaire(){
        Exposition expo=new Exposition();
        Tableau tab1= new Tableau();
        Transaction transac1 = new Transaction();
        transac1.setOeuvre(tab1);
        transac1.setPrixVente(10);
        Tableau tab2= new Tableau();
        Transaction transac2 = new Transaction();
        transac2.setOeuvre(tab2);
        transac2.setPrixVente(10);
        transac1.setLieuDeVente(expo);
        transac2.setLieuDeVente(expo);
        ArrayList<Transaction> ventes = new ArrayList();
        ventes.add(transac1);
        ventes.add(transac2);
        expo.setVentes(ventes);
        
        assertEquals(20, expo.chiffreAffaire(), "On doit trouver 20" );
        
        assertEquals(20, expositionDAO.chiffreAffairePour(1), "On doit trouver 20" );
    }

}
