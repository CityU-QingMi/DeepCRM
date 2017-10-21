  @Override
  public void storeTrigger(OperableTrigger newTrigger, boolean replaceExisting) throws JobPersistenceException {
    OperableTrigger clone = (OperableTrigger) newTrigger.clone();

    lock();
    try {
      JobDetail job = retrieveJob(newTrigger.getJobKey());
      if (job == null) {
        //
        throw new JobPersistenceException("The job (" + newTrigger.getJobKey()
                                          + ") referenced by the trigger does not exist.");
      }

      // wrapper construction must be done in lock since serializer is unlocked
      TriggerWrapper tw = wrapperFactory.createTriggerWrapper(clone, job.isConcurrentExectionDisallowed());

      if (triggerFacade.containsKey(tw.getKey())) {
        if (!replaceExisting) { throw new ObjectAlreadyExistsException(newTrigger); }

        removeTrigger(newTrigger.getKey(), false);
      }

      // add to triggers by group
      Set<String> grpSet = toolkitDSHolder.getOrCreateTriggersGroupMap(newTrigger.getKey().getGroup());
      grpSet.add(newTrigger.getKey().getName());
      if (!triggerFacade.hasGroup(newTrigger.getKey().getGroup())) {
        triggerFacade.addGroup(newTrigger.getKey().getGroup());
      }

      if (triggerFacade.pausedGroupsContain(newTrigger.getKey().getGroup())
          || jobFacade.pausedGroupsContain(newTrigger.getJobKey().getGroup())) {
        tw.setState(TriggerState.PAUSED, terracottaClientId, triggerFacade);
        if (jobFacade.blockedJobsContain(tw.getJobKey())) {
          tw.setState(TriggerState.PAUSED_BLOCKED, terracottaClientId, triggerFacade);
        }
      } else if (jobFacade.blockedJobsContain(tw.getJobKey())) {
        tw.setState(TriggerState.BLOCKED, terracottaClientId, triggerFacade);
      } else {
        timeTriggers.add(tw);
      }

      // add to triggers by FQN map
      triggerFacade.put(tw.getKey(), tw);
    } finally {
      unlock();
    }
  }
