import org.example.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstTest {

    @BeforeEach
    void setUp() {
        //
    }

    @Test
    void printDeck() {
        Main.listDeck();

    }
}
