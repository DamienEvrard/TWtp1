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
    @NonNull
    private Personne client;
    
    @OneToOne (cascade = CascadeType.ALL)
    @NonNull
    @JoinColumn  (name = "oeuvre_id", unique = true)
    private  Tableau oeuvre;
    
    @ManyToOne
    @NonNull
    private Exposition lieuDeVente;
}
