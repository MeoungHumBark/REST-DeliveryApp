package FoodDeliveryApp.Food;

import FoodDeliveryApp.Food.Food;
import FoodDeliveryApp.Food.FoodDTO;
import FoodDeliveryApp.Food.FoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/foods")
@Tag(name = "Food API", description = "음식 조회,저장,수정,삭제 기능 구현")
public class FoodController {
    private final FoodService foodService;
    
    @Autowired
    public FoodController (FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping()
    @Operation(summary = "Get a list of all foods", description = "Retrieve a list of all available foods")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Food found"),
    @ApiResponse(responseCode = "404", description = "Food not found")})
    ResponseEntity<List<FoodDTO>> getAllFoods()  {
        if (foodService.getAll()==null) {
            return ResponseEntity.notFound().build();
        }
        List<FoodDTO> allFood = foodService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allFood);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a food by ID", description = "Retrieve a food by its ID")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200",
            description = "Food found",
            content = @Content(schema = @Schema(implementation = Food.class))
    ), @ApiResponse(responseCode = "404", description = "Food not found")})
    ResponseEntity<FoodDTO> getFood(@PathVariable @Parameter(description = "Food ID", example = "1") Integer id) {
        if (foodService.get(id)==null) {
            return ResponseEntity.notFound().build();
        }
        FoodDTO selectedFood = foodService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(selectedFood);
    }

    @PostMapping()
    @Operation(summary = "Add a new food")
    @ApiResponse(responseCode = "201", description = "Food added successfully")
    ResponseEntity<FoodDTO> setFood(@RequestBody FoodDTO foodDTO) {
        foodService.set(foodDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodDTO);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a food", description = "Update an existing food in the list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Food updated successfully"),
            @ApiResponse(responseCode = "404", description = "Food not found")
    })
    ResponseEntity<FoodDTO> updateFood(@PathVariable Integer id, @RequestBody FoodDTO foodDTO) {
        if (foodService.get(id)==null) {
            return ResponseEntity.notFound().build();
        }
        foodService.update(id,foodDTO);
        return ResponseEntity.status(HttpStatus.OK).body(foodDTO);
    }

    @DeleteMapping()
    @Operation(summary = "Delete all foods", description = "Delete all foods from the list")
    ResponseEntity<String> delAllFood()  {
        foodService.delAll();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

    @DeleteMapping ("{id}")
    @Operation(summary = "Delete a food", description = "Delete an existing food from the list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Food deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Food not found")
    })
    ResponseEntity<String> deleteFood(@PathVariable Integer id) {
        if (foodService.get(id)==null) {
            return ResponseEntity.notFound().build();
        }
        foodService.del(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
    }

}