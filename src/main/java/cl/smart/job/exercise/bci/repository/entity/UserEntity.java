package cl.smart.job.exercise.bci.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false, unique = true)
    private String userName;
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    @Column(length = 8, nullable = false)
    private String password;
    @Column(length = 1, nullable = false)
    private String state;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime creationDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;
}
