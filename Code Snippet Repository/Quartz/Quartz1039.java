  private void scheduleRecoveryIfNeeded(TriggerWrapper tw, FiredTrigger recovering) {
    JobWrapper jobWrapper = jobFacade.get(tw.getJobKey());

    if (jobWrapper == null) {
      getLog().error("No job found for orphaned trigger: " + tw);
      return;
    }

    if (jobWrapper.requestsRecovery()) {
      OperableTrigger recoveryTrigger = createRecoveryTrigger(tw, jobWrapper, "recover_" + terracottaClientId + "_"
                                                                          + ftrCtr++, recovering);

      JobDataMap jd = tw.getTriggerClone().getJobDataMap();
      jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_NAME, tw.getKey().getName());
      jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_GROUP, tw.getKey().getGroup());
      jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_FIRETIME_IN_MILLISECONDS, String.valueOf(recovering.getFireTime()));
      jd.put(Scheduler.FAILED_JOB_ORIGINAL_TRIGGER_SCHEDULED_FIRETIME_IN_MILLISECONDS, String.valueOf(recovering.getScheduledFireTime()));

      recoveryTrigger.setJobDataMap(jd);
      recoveryTrigger.computeFirstFireTime(null);

      try {
        storeTrigger(recoveryTrigger, false);
        if (!tw.mayFireAgain()) {
          removeTrigger(tw.getKey());
        }
        getLog().info("Recovered job " + jobWrapper + " for trigger " + tw);
      } catch (JobPersistenceException e) {
        getLog().error("Can't recover job " + jobWrapper + " for trigger " + tw, e);
      }
    }
  }
