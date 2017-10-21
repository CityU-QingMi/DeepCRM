  @Override
  public void storeJob(JobDetail newJob, boolean replaceExisting) throws ObjectAlreadyExistsException,
      JobPersistenceException {
    JobDetail clone = (JobDetail) newJob.clone();

    lock();
    try {
      // wrapper construction must be done in lock since serializer is unlocked
      JobWrapper jw = wrapperFactory.createJobWrapper(clone);

      if (jobFacade.containsKey(jw.getKey())) {
        if (!replaceExisting) { throw new ObjectAlreadyExistsException(newJob); }
      } else {
        // get job group
        Set<String> grpSet = toolkitDSHolder.getOrCreateJobsGroupMap(newJob.getKey().getGroup());
        // add to jobs by group
        grpSet.add(jw.getKey().getName());

        if (!jobFacade.hasGroup(jw.getKey().getGroup())) {
          jobFacade.addGroup(jw.getKey().getGroup());
        }
      }

      // add/update jobs FQN map
      jobFacade.put(jw.getKey(), jw);
    } finally {
      unlock();
    }
  }
