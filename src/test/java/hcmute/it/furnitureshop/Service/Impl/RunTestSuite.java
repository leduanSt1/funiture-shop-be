package hcmute.it.furnitureshop.Service.Impl;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RunTestSuite {
    public static void main(String[] args) {
        Result result=JUnitCore.runClasses(TestSuite.class);
        for (Failure failure: result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
