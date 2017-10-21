    private void processFile(JobFile jobFile) {
        if (jobFile == null || !jobFile.getFileFound()) {
            return;
        }


        try {
            XMLSchedulingDataProcessor processor = 
                new XMLSchedulingDataProcessor(this.classLoadHelper);
            
            processor.addJobGroupToNeverDelete(JOB_INITIALIZATION_PLUGIN_NAME);
            processor.addTriggerGroupToNeverDelete(JOB_INITIALIZATION_PLUGIN_NAME);
            
            processor.processFileAndScheduleJobs(
                    jobFile.getFileName(), 
                    jobFile.getFileName(), // systemId 
                    getScheduler());
        } catch (Exception e) {
            getLog().error("Error scheduling jobs: " + e.getMessage(), e);
        }
    }
