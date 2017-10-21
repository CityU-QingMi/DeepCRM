    public void processFileAndScheduleJobs(Scheduler sched,
            boolean overWriteExistingJobs) throws Exception {
        String fileName = QUARTZ_XML_DEFAULT_FILE_NAME;
        processFile(fileName, getSystemIdForFileName(fileName));
        // The overWriteExistingJobs flag was set by processFile() -> prepForProcessing(), then by xml parsing, and then now
        // we need to reset it again here by this method parameter to override it.
        setOverWriteExistingData(overWriteExistingJobs);
        executePreProcessCommands(sched);
        scheduleJobs(sched);
    }
