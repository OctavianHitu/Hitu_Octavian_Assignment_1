package ro.tuc.ds2020.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Measurements {

    private static final long serialVersionUID =1L;


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")

    private UUID id;


    @Column(name="timestamp")
    private Timestamp timestamp;


    @Column(name = "energyConsuption")
    private int energyConsumption;

    @ManyToOne
    @JoinColumn(name="device_id")
    private Device device;

    public Measurements(Timestamp timestamp, int energyConsumption) {
        this.timestamp=timestamp;
        this.energyConsumption=energyConsumption;
    }
}
