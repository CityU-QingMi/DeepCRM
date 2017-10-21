    @Override
    public void start(UserTransaction userTransaction) {
        try {
            if (jobFiles.isEmpty() == false) {
                
                if (scanInterval > 0) {
                    getScheduler().getContext().put(JOB_INITIALIZATION_PLUGIN_NAME + '_' + getName(), this);
                }
                
                Iterator<JobFile> iterator = jobFiles.values().iterator();
                while (iterator.hasNext()) {
                    JobFile jobFile = iterator.next();
                
                    if (scanInterval > 0) {
                        String jobTriggerName = buildJobTriggerName(jobFile.getFileBasename());
                        TriggerKey tKey = new TriggerKey(jobTriggerName, JOB_INITIALIZATION_PLUGIN_NAME);
                        
                        // remove pre-existing job/trigger, if any
                        getScheduler().unscheduleJob(tKey);

                        JobDetail job = newJob().withIdentity(jobTriggerName, JOB_INITIALIZATION_PLUGIN_NAME).ofType(FileScanJob.class)
                            .usingJobData(FileScanJob.FILE_NAME, jobFile.getFileName())
                            .usingJobData(FileScanJob.FILE_SCAN_LISTENER_NAME, JOB_INITIALIZATION_PLUGIN_NAME + '_' + getName())
                            .build();

                        SimpleTrigger trig = newTrigger().withIdentity(tKey).withSchedule(
                                simpleSchedule().repeatForever().withIntervalInMilliseconds(scanInterval))
                                .forJob(job)
                                .build();

                        getScheduler().scheduleJob(job, trig);
                        getLog().debug("Scheduled file scan job for data file: {}, at interval: {}", jobFile.getFileName(), scanInterval);
                    }
                    
                    processFile(jobFile);
                }
            }
        } catch(SchedulerException se) {
            getLog().error("Error starting background-task for watching jobs file.", se);
        } finally {
            started = true;
        }
    }
