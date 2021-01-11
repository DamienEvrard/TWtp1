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
    private Date debut;
    
    @NonNull
    private String intitule;
    
    @NonNull
    private int duree;
    
    @ManyToOne(optional = false)
    @NonNull
    private Galerie oraganisateur;    
    
    @ManyToMany
    @JoinTable(name="accrochage",joinColumns = @JoinColumn(name="exposition_id"),inverseJoinColumns = @JoinColumn(name="tableau_id"))
    private List<Tableau> accrochage;
    
    @OneToMany(mappedBy = "lieuDeVente", cascade= CascadeType.PERSIST)
    private List<Transaction> ventes;
}
