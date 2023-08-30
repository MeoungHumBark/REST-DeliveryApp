package FoodDeliveryApp.Food;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FoodDTO {

    private int id;

    private String name;

    private int price;

}
