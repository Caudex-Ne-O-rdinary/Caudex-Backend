package hackerton.caudex.domain.plant.entity;

import hackerton.caudex.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "plant")
public class Plant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_participant")
    private GardenParticipant gardenParticipant;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String image_url;
}
