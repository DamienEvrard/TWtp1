package galerie.dao;

import galerie.entity.Galerie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import galerie.dao.GalerieRepository;
import galerie.entity.Exposition;
import galerie.entity.Tableau;
import galerie.entity.Transaction;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class GalerieRepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;
    private Galerie galerie, galerie2;
    
    
    @BeforeEach
    public void setUp() {
         galerie = new Galerie("galerie2","adresse2");
         galerie2 = new Galerie("galerie3","adresse3");
    }

    @Test
    @Sql("test-data_galerie.sql") // On peut charger des donnnées spécifiques pour un test
    public void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Galerie'");
        int combienDansLeJeuDeTest = 1; 
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre, "On doit trouver 1 galerie" );
    }
    
    @Test
    @Sql("test-data_galerie.sql")
    public void insertGalerie(){
        galerieDAO.save(galerie);
        long nombre = galerieDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 galerie" );
    }
    
    
    @Test
    @Sql("test-data_galerie.sql")
    public void deleteGalerie(){
        galerieDAO.save(galerie);
        galerieDAO.save(galerie2);
        galerieDAO.delete(galerie);
        long nombre = galerieDAO.count();
        assertEquals(2, nombre, "On doit trouver 2 galerie" );
    }
    
    
    @Test
    @Sql("test-data_galerie.sql")
    public void ehiffreAffaire(){
        Galerie galerie = new Galerie();
        Exposition expo=new Exposition();
        Exposition expo1=new Exposition();
        
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
        
        Date date= new Date(2021, 01, 20);
        Date date1= new Date(2020, 01, 20);
        expo.setDebut(date);
        expo1.setDebut(date1);
        
        Tableau tab3= new Tableau();
        Transaction transac3 = new Transaction();
        transac3.setOeuvre(tab3);
        transac3.setPrixVente(10);
        ArrayList<Transaction> ventes2 = new ArrayList();
        ventes2.add(transac3);
        expo1.setVentes(ventes2);
        
        ArrayList<Exposition> expositions = new ArrayList();
        expositions.add(expo);
        expositions.add(expo1);
        galerie.setExpositions(expositions);
        
        assertEquals(20, galerie.ChiffreAffaire(2021), "On doit trouver 20" );
    }
    
    

}
