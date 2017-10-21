    protected void prepForProcessing()
    {
        clearValidationExceptions();
        
        setOverWriteExistingData(true);
        setIgnoreDuplicates(false);

        jobGroupsToDelete.clear();
        jobsToDelete.clear();
        triggerGroupsToDelete.clear();
        triggersToDelete.clear();
        
        loadedJobs.clear();
        loadedTriggers.clear();
    }
