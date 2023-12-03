package FoodDeliveryApp.Food;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class FoodService {
    private final JpaFoodRepository foodRepository;
    @Autowired
    public FoodService (JpaFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<FoodDTO> getAll() {
        return foodRepository.findAll().stream()
                .map(FoodMapper::mapEntityToDto)
                .collect(toList());
    }
    public FoodDTO get(Integer id) {
        Optional<Food> foodEntity = foodRepository.findById(id);
        return FoodMapper.mapEntityToDto(foodEntity.get());
    }

    public void set(FoodDTO foodDTO) {
        Food foodEntity = FoodMapper.mapDtoToEntity(foodDTO);
        foodRepository.save(foodEntity);
    }
    public void update(Integer id, FoodDTO foodDTO) {
        Optional<Food> existingFoodEntity = foodRepository.findById(id);

        if (existingFoodEntity != null) {
            Food updatedFoodEntity = FoodMapper.mapDtoToEntity(foodDTO);
            updatedFoodEntity.setId(id);
            foodRepository.save(updatedFoodEntity);
        }
    }

    public void delAll() {
        foodRepository.deleteAll();
    }
    public void del(Integer id) {
        foodRepository.deleteById(id);
    }

}
