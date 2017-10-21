    public Map<String, Long> getPerformanceMetrics() {
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("JobsCompleted", Long
                .valueOf(getJobsCompletedMostRecentSample()));
        result.put("JobsExecuted", Long
                .valueOf(getJobsExecutedMostRecentSample()));
        result.put("JobsScheduled", Long
                .valueOf(getJobsScheduledMostRecentSample()));
        return result;
    }
