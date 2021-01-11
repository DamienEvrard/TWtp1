package galerie.entity;
import java.util.Date;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Transaction {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private Date venduLe;
    
    @NonNull
    private float prixVente;
    
    @ManyToOne
    private Personne client;
    
    @OneToOne
    private Tableau oeuvre;
    
    @ManyToOne
    private Exposition lieuDeVente;
}
