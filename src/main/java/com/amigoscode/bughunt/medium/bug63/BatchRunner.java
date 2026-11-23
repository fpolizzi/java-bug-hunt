package com.amigoscode.bughunt.medium.bug63;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs batch jobs that require resource setup and teardown.
 * Each job consists of initialization, processing, and cleanup phases.
 */
public class BatchRunner {

    private final List<String> executionLog = new ArrayList<>();

    /**
     * Represents a batch job with lifecycle methods.
     */
    public interface BatchJob {
        void initialize() throws Exception;

        String process() throws Exception;

        void cleanup() throws Exception;
    }

    /**
     * Executes a batch job: initializes, processes, then cleans up.
     * Returns the processing result.
     *
     * BUG: If process() throws and cleanup() also throws,
     * the cleanup exception masks the original processing exception.
     * The caller never sees the real cause of failure.
     */
    public String execute(BatchJob job) throws Exception {
        job.initialize();
        executionLog.add("INITIALIZED");

        Exception primaryException = null;
        try {
            String result = job.process();
            executionLog.add("PROCESSED");
            return result;
        } catch (Exception e) {
            primaryException = e;
            throw e;
        } finally {
            try {
                job.cleanup();
                executionLog.add("CLEANED_UP");
            } catch (Exception cleanupEx) {
                executionLog.add("CLEANED_UP");
                if (primaryException != null) {
                    primaryException.addSuppressed(cleanupEx);
                } else {
                    throw cleanupEx;
                }
            }
        }
    }

    /**
     * Executes multiple batch jobs, collecting results.
     * Stops at the first failure.
     */
    public List<String> executeAll(List<BatchJob> jobs) throws Exception {
        List<String> results = new ArrayList<>();
        for (BatchJob job : jobs) {
            results.add(execute(job));
        }
        return results;
    }

    /**
     * Returns the execution log entries.
     */
    public List<String> getExecutionLog() {
        return new ArrayList<>(executionLog);
    }

    /**
     * Returns whether the last execution completed all phases.
     */
    public boolean lastRunFullyCompleted() {
        if (executionLog.size() < 3) {
            return false;
        }
        int size = executionLog.size();
        return executionLog.get(size - 3).equals("INITIALIZED")
                && executionLog.get(size - 2).equals("PROCESSED")
                && executionLog.get(size - 1).equals("CLEANED_UP");
    }

    /**
     * Clears the execution log.
     */
    public void clearLog() {
        executionLog.clear();
    }
}
