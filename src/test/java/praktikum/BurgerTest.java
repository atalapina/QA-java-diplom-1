package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

public class BurgerTest {


    private Database database;
    private static final double DELTA = 1e-15;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void burgerSetBuns() {
        var burger = new Burger();
        for (var bunSaved : database.availableBuns()) {
            burger.setBuns(bunSaved);
            Assert.assertEquals(bunSaved, burger.bun);
        }

    }

    @Test
    public void burgerAddIngredient() {
        var burger = new Burger();
        for (var ingredientSaved : database.availableIngredients()) {
            burger.addIngredient(ingredientSaved);
            Assert.assertTrue(burger.ingredients.contains(ingredientSaved));
        }
    }
    @Test
    public void burgerRemoveIngredient() {
        var burger = new Burger();
        for (var ingredientSaved : database.availableIngredients()) {
            burger.addIngredient(ingredientSaved);
        }
        int size = burger.ingredients.size();
        burger.removeIngredient(size - 1);
        Assert.assertEquals(size - 1, burger.ingredients.size());
    }
    @Test
    public void burgerGetPrice() {
        var burger = new Burger();
        Mockito.when(ingredient.getPrice()).thenReturn(10F);
        Mockito.when(bun.getPrice()).thenReturn(1F);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Assert.assertEquals(10 + 2F, burger.getPrice(), DELTA);
    }
    @Test
    public void burgerGetReceipt(){
        var burger = new Burger();
        burger.setBuns(new Bun("Test bun", 10));

        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "red", 3));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "pepperoni", 7));

        String stringReciept = "(==== Test bun ====)" + System.getProperty("line.separator") +
                "= sauce red =" +
                System.getProperty("line.separator") +
                "= filling pepperoni =" +
                System.getProperty("line.separator") +
                "(==== Test bun ====)" +
                System.getProperty("line.separator") +
                System.getProperty("line.separator") +
                "Price: 30,000000" +
                System.getProperty("line.separator");
        Assert.assertEquals(stringReciept, burger.getReceipt());
    }

    @Test
    public void burgerMoveIngredient(){
        var burger = new Burger();
        burger.setBuns(new Bun("Test bun", 10));
        var ingredient0 = new Ingredient(IngredientType.SAUCE, "red", 3);
        var ingredient1 = new Ingredient(IngredientType.FILLING, "pepperoni", 7);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.moveIngredient(1, 0);
        Assert.assertEquals(ingredient0, burger.ingredients.get(1));
        Assert.assertEquals(ingredient1, burger.ingredients.get(0));
    }
}
