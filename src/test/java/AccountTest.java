import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AccountTest {
    private String name;
    private boolean isNameCorrect;


    public AccountTest (String name, boolean isNameCorrect) {
        this.name = name;
        this.isNameCorrect = isNameCorrect;
    }

    @Parameterized.Parameters
    public static Object [][] getNameAccount() {
        return new Object[][] {
                {"Тимоти Шаламе", true},
                {"тимоти шаламе", true},
                {"т ш", true},
                {"Тимоти Шаламеаааааа", true}, // 19 символов
                {"Тимоти Шаламеааааааа", false}, // 20 символов
                {"Timoti Shakame", false}, // English
                {"ТимотиШаламе", false},
                {"Тим Петрович Шалам", false},
                {" Тимоти Шаламе ", false},
                {" Тимоти Шаламе", false},
                {"Тимоти Шаламе ", false},
                {"Тимоти  Шаламе", false}, // два пробела
                {"ТимотиШаламе ", false}, // пробел в конце (единственный)
                {" ТимотиШаламе", false}, // пробел вначале (единственный)
                {"1 3", false},
                {"ТШ", false},
                {"", false},
                {null, false}
        };
    }

    @Test
    @DisplayName("Проверка метода checkNameToEmboss")
    public void checkNameToEmbossTest() {
        try {
            Account account = new Account(name);
            Assert.assertEquals(name + ": не прошел проверку", isNameCorrect, account.checkNameToEmboss());
        } catch (Exception exception) {
            assert exception instanceof NullPointerException;
        }
    }
}