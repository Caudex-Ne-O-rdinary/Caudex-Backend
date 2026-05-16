package hackerton.caudex.domain.plant.entity;

import hackerton.caudex.domain.diary.entity.Diary;
import hackerton.caudex.domain.garden.entity.GardenParticipant;
import hackerton.caudex.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "garden_participant", nullable = false)
    private GardenParticipant gardenParticipant;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "ratio_x", nullable = false)
    private Double ratioX;

    @Column(name = "ratio_y", nullable = false)
    private Double ratioY;

    @Column(name = "management_tip", nullable = false)
    private String managementTip;

    @OneToMany(mappedBy = "plant")
    private List<Diary> diaries = new ArrayList<>();
  
    public void updatePosition(Double ratioX, Double ratioY){
        this.ratioX = ratioX;
        this.ratioY = ratioY;
    }
}
