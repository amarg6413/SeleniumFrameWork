package Utils;
;
import com.aventstack.extentreports.Status;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class Assertions<T> extends SoftAssert {

    private static Log log =new Log();
    static String result = "";
    T driver = null;

    public Assertions(T driver) {
        if (driver != null) {
            this.driver = driver;
        }

    }

    public void onAssertSuccess(IAssert<?> iAssert) {
        super.onAssertSuccess(iAssert);
        log.info(Status.PASS,"Expected Value: " + iAssert.getExpected() + " ,Actual Value: " + iAssert.getActual());
    }

    public void onAssertFailure(IAssert<?> iAssert, AssertionError assertionError) {
        super.onAssertFailure(iAssert, assertionError);
        log.info(Status.FAIL,"Expected Value: " + iAssert.getExpected() + " ,Actual Value: " + iAssert.getActual());
    }

}
