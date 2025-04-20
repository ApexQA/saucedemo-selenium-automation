package SwagLabs.utilities;

import org.testng.asserts.SoftAssert;

public class CustomSoftAssertions extends SoftAssert {
    public static CustomSoftAssertions softAssertion = new CustomSoftAssertions();
    public static void customAssertAll(){
        try {
            softAssertion.assertAll("Custom Soft Assertion");
        }
        catch (Exception e){
            System.out.println("Custom Soft Assertion Failed");
        }
    }

}