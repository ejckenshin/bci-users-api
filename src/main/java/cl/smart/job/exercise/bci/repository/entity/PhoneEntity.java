package cl.smart.job.exercise.bci.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "phone")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 12, nullable = false)
    private String number;
    @Column(length = 2, nullable = false)
    private String cityCode;
    @Column(name = "country_code", length = 3, nullable = false)
    private String countryCode;
    @Column(length = 1, nullable = false)
    private String state;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime creationDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
