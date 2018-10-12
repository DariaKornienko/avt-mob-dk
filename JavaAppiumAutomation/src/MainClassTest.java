import org.junit.Assert;
import org.junit.Test;

public class MainClassTest
{
    MainClass check = new MainClass();

    @Test
    public void testGetClassString()
    {
        String a = "Hello";
        String b = "hello";
        String text = check.getClassString();

        if (text.contains(a) || text.contains(b)) {
            System.out.println("Text contains “hello” or “Hello”");
        } else {
            Assert.fail("Substring not found");
        }
    }
}
