package ro.tuc.ds2020.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Device {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")

    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="adress")
    private String adress;

    @Column(name="energyConsumption")
    private int energyConsumption;

    @ManyToOne
    @JoinColumn(name="userOfApp_id")
    private UserOfApp userOfApp;

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL)
    private List<Measurements> measurements;


    public Device(String name,String description, String adress, int energyConsuption) {
        this.name=name;
        this.description=description;
        this.adress=adress;
        this.energyConsumption=energyConsuption;

    }
}
