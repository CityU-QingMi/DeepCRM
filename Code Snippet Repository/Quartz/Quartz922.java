    private String buildJobTriggerName(
            String fileBasename) {
        // Name w/o collisions will be prefix + _ + filename (with '.' of filename replaced with '_')
        // For example: JobInitializationPlugin_jobInitializer_myjobs_xml
        String jobTriggerName = JOB_INITIALIZATION_PLUGIN_NAME + '_' + getName() + '_' + fileBasename.replace('.', '_');
        
        // If name is too long (DB column is 80 chars), then truncate to max length
        if (jobTriggerName.length() > MAX_JOB_TRIGGER_NAME_LEN) {
            jobTriggerName = jobTriggerName.substring(0, MAX_JOB_TRIGGER_NAME_LEN);
        }
        
        // Make sure this name is unique in case the same file name under different
        // directories is being checked, or had a naming collision due to length truncation.
        // If there is a conflict, keep incrementing a _# suffix on the name (being sure
        // not to get too long), until we find a unique name.
        int currentIndex = 1;
        while (jobTriggerNameSet.add(jobTriggerName) == false) {
            // If not our first time through, then strip off old numeric suffix
            if (currentIndex > 1) {
                jobTriggerName = jobTriggerName.substring(0, jobTriggerName.lastIndexOf('_'));
            }

            String numericSuffix = "_" + currentIndex++;

            // If the numeric suffix would make the name too long, then make room for it.
            if (jobTriggerName.length() > (MAX_JOB_TRIGGER_NAME_LEN - numericSuffix.length())) {
                jobTriggerName = jobTriggerName.substring(0, (MAX_JOB_TRIGGER_NAME_LEN - numericSuffix.length()));
            }

            jobTriggerName += numericSuffix;
        }
        
        return jobTriggerName;
    }
