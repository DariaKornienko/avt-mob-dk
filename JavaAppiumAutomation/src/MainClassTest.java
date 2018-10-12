import org.junit.Test;

public class MainClassTest
{
    MainClass check = new MainClass();

    @Test
    public void testGetClassNumber()
    {
        if (check.getClassNumber() > 45) {
            System.out.println("this number is greater than 45");
        } else {
            System.out.println("this number is not greater than 45");
        }
    }
}
