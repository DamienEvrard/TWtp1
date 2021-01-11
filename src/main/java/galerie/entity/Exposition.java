package galerie.entity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private Date dateE;
    
    @NonNull
    private String intitule;
    
    @NonNull
    private int duree;
    
    @ManyToOne(optional = false)
    private Galerie oraganisateur;    
    
    @ManyToMany
    private List<Tableau> tableaux;
    
    @OneToMany(mappedBy = "lieuDeVente", cascade= CascadeType.PERSIST)
    private List<Transaction> ventes;
}
