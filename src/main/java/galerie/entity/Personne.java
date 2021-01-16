package galerie.entity;
import java.util.List;
import javax.persistence.*;
import lombok.*;


@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity
public class Personne {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String nom;
    
    @NonNull
    private String adresse;
    
    @OneToMany(mappedBy = "client", cascade= CascadeType.PERSIST)
    private List<Transaction> achat;
    
    
    public int budgetArt(int annee){
        int budget = 0;
        
        for(Transaction t : achat){
            if(t.getVenduLe().getYear()==annee){
                budget+=t.getPrixVente();
            }
            
        }
        return budget;
    }
    
}
