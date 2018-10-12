import org.junit.Test;

public class MainClassTest
{
    MainClass check = new MainClass();

    @Test
    public void testGetLocalNumber()
    {
        if (check.getLocalNumber() == 14) {
            System.out.println("The value is equal to 14");
        } else {
            System.out.println("The value is not equal to 14");
        }
    }
}
