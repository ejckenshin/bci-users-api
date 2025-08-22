package cl.smart.job.exercise.bci.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "login_access")
public class LoginAccessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    private String userName;
    private String token;
    @Column(columnDefinition = "TIMESTAMP")
    private Instant expiryDate;
    @Column(length = 1, nullable = false)
    private String status;
    private Integer attempts;
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime lastLogin;
}
