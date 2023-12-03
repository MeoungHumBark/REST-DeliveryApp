package FoodDeliveryApp.Food;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name="food")
public class Food {
   @Id
   @Column(name="foodno", nullable = false)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;
}
