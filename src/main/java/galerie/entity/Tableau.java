package galerie.entity;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Tableau {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String titre;
    
    @NonNull
    private String support;
    
    @NonNull
    private int largeur;
    
    @NonNull
    private int hauteur;
    
    @ManyToMany(mappedBy = "accrochage")
    @NonNull
    private List<Exposition> organisateur;
    
    @OneToOne
    private Transaction vente;
    
    @ManyToOne(optional = false)
    @NonNull
    private Artiste peintre;
}
