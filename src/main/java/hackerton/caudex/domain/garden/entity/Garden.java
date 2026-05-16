package hackerton.caudex.domain.garden.entity;

import hackerton.caudex.global.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "garden")
public class Garden extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @Column(name="uuid", length=36, nullable = false)
    private String uuid;

    @Column(name="name", length=100, nullable = false)
    private String name;

    @Column(name = "canvas_data", columnDefinition = "LONGTEXT", nullable = false)
    private String canvasData;
}
