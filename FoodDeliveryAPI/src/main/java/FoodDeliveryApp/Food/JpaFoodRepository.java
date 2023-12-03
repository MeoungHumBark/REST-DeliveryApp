package FoodDeliveryApp.Food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFoodRepository extends JpaRepository<Food,Integer> {
}


