package by.onliner.utils.listeners;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

@Log4j2
public class TestListener implements TestWatcher, BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("Test starting: {}", context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        log.info("Test finished: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.error("Test FAILED: {}. Reason: {}", context.getDisplayName(), cause.getMessage());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test SUCCEEDED: {}", context.getDisplayName());
    }
}
