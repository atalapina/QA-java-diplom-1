package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class BunTest {
    private Database database;
    private static final double DELTA = 1e-15;
    @Before
    public void setUp(){
        database = new Database();
    }
    @Test
    public void bunGetName() {
        for(var bunSaved : database.availableBuns()){
            Bun bun = new Bun(bunSaved.name, bunSaved.price);
            Assert.assertEquals(bunSaved.name, bun.getName());
        }
    }
    @Test
    public void bunGetPrice() {
        for(var bunSaved : database.availableBuns()){
            Bun bun = new Bun(bunSaved.name, bunSaved.price);
            Assert.assertEquals(bunSaved.price, bun.getPrice(), DELTA);
        }
    }



}
