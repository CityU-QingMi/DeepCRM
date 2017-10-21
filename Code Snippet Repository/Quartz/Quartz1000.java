  @Override
  protected void test0(Scheduler scheduler) throws Throwable {
    // assert some things about the triggers and jobs that exist
    List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
    if (triggerGroupNames.size() != 1) { throw new AssertionError("wrong number of trigger groups: "
                                                                  + Arrays.asList(triggerGroupNames).toString()); }

    Set<TriggerKey> triggerNames = scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupNames.get(0)));
    if (triggerNames.size() != 2) { throw new AssertionError("wrong number of triggers: " + Arrays.asList(triggerNames)); }

    assertTrigger(scheduler, "simpleTrigger", triggerGroupNames.get(0));
    assertTrigger(scheduler, "cronTrigger", triggerGroupNames.get(0));

    List<String> jobGroupNames = scheduler.getJobGroupNames();
    if (jobGroupNames.size() != 1) { throw new AssertionError("wrong number of job groups: "
                                                              + Arrays.asList(jobGroupNames).toString()); }

    assertJob(scheduler, "exampleJob", jobGroupNames.get(0));

    SimpleSpringClient1.localBarrier.await();
  }
