package AssignmentFinal.core;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * If a test case fails during execution, it will be rerun a number of times specified by maxRetryCount in this vlass.
 * Otherwise, this class will not be invoked.
 */
public class RetryFailedTestCase implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 3; // Maximum retry times before return failed status

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
