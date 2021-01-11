package galerie.entity;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @RequiredArgsConstructor @ToString
@Entity
public class Artiste extends Personne{
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    
    private String bibliographie;
    
    @OneToMany(mappedBy = "peintre", cascade= CascadeType.PERSIST)
    private List<Tableau> oeuvres;
}
