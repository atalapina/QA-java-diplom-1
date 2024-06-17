package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class IngredientTest {
    private Database database;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void ingredientGetName() {
        for (var ingredientSaved : database.availableIngredients()) {
            Ingredient ingredient = new Ingredient(ingredientSaved.type, ingredientSaved.name, ingredientSaved.price);
            Assert.assertEquals(ingredientSaved.name, ingredient.getName());
        }
    }

    @Test
    public void ingredientGetPrice() {
        for (var ingredientSaved : database.availableIngredients()) {
            Ingredient ingredient = new Ingredient(ingredientSaved.type, ingredientSaved.name, ingredientSaved.price);
            Assert.assertEquals(ingredientSaved.price, ingredient.getPrice(), DELTA);
        }
    }

    @Test
    public void ingredientGetType() {
        for (var ingredientSaved : database.availableIngredients()) {
            Ingredient ingredient = new Ingredient(ingredientSaved.type, ingredientSaved.name, ingredientSaved.price);
            Assert.assertEquals(ingredientSaved.type, ingredient.getType());
        }
    }

}