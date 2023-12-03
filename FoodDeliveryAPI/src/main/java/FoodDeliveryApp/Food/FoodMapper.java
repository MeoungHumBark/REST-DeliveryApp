package FoodDeliveryApp.Food;

public class FoodMapper {
    public static Food mapDtoToEntity(FoodDTO dto) {
        return Food.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

    public static FoodDTO mapEntityToDto(Food entity) {
        return FoodDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}
