import org.example.ProstoClass;
import org.example.annotations.Test;

public class ProstTest {

    @Test
    public void ProstTest() {
        ProstoClass prostoClass = new ProstoClass();
        prostoClass.ask("В чём смысл всего и вообще?");
        assert prostoClass.vChyomSmyslVsegoIVoobcshe == 42;
    }
}
