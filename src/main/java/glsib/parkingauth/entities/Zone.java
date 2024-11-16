package glsib.parkingauth.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long id;

    @Column(nullable = false)
    double latitude;

    @Column(nullable = false)
    double longitude;

    @Column(length = 255)
    String description;

    @Column(nullable = false)
    double price;

    @Column(name = "total_spots", nullable = false)
    int totalSpots;

    @Column(name = "available_spots", nullable = false)
    int availableSpots;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Date updatedAt;

    // Getters and Setters will be automatically generated by Lombok

}