package listeners;

import org.apache.commons.io.FileUtils;
import org.testng.*;
import utilities.AllureUtils;
import utilities.FilesUtils;
import utilities.LogsUtil;
import utilities.ScreenshotsUtils;

import java.io.File;
import java.io.IOException;

import static utilities.PropertiesUtils.loadProperties;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {
    File allureResults = new File("test-outputs/allure-results");
    File logs = new File("test-outputs/Logs");
    File screenshots = new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Logs Execution Started");
        loadProperties();
        FilesUtils.deleteFiles(allureResults);
        //FilesUtils.cleanDirectory(logs);
        FilesUtils.cleanDirectory(screenshots);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Logs Execution Finished");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            switch (testResult.getStatus())
            {
                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot("skipped-" + testResult.getName());
            }
            AllureUtils.attachedLogsToAllureReport();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result){
        LogsUtil.info("Test case", result.getTestName(), "passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        LogsUtil.info("Test case", result.getTestName(), "failed");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        LogsUtil.info("Test case", result.getTestName(), "skipped");
    }

}
