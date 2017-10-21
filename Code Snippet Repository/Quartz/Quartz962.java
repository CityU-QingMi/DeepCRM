  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    JobDetailImpl jobDetail = new JobDetailImpl("futurejob", null, NullJob.class);
    jobDetail.setDurability(true);

    SimpleTriggerImpl trigger = new SimpleTriggerImpl("futuretrigger", "group");
    // run a week from now (ie. don't run)
    trigger.setStartTime(new Date(System.currentTimeMillis() + (1000L * 60L * 60L * 24L * 7L)));
    trigger.setJobName("futurejob");

    scheduler.addJob(jobDetail, false);
    scheduler.scheduleJob(trigger);

    // not strictly necessary but this should make the newly added trigger get ACQUIRED locally
    Thread.sleep(5000L);

    Socket s;

    // make this loop forever in case a hang in the first client prevents the listener from ever starting. This loop
    // should make the test timeout and thus we'll get a thread dump
    while (true) {
      try {
        s = new Socket("127.0.0.1", node1Port);
        break;
      } catch (ConnectException ce) {
        Thread.sleep(1000L);
      }
    }

    InputStream in = s.getInputStream();

    final long end = System.currentTimeMillis() + 60000L;
    while (System.currentTimeMillis() < end) {
      log("Sleeping");
      Thread.sleep(1000L);

      try {
        if (in.read() < 0) {
          break;
        }
      } catch (IOException ioe) {
        break;
      }
    }

    if (System.currentTimeMillis() > end) { throw new AssertionError(); }

    SimpleJob.localBarrier.await();
  }
