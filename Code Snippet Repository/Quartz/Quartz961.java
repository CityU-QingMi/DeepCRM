  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    log("Starting listening on " + listenPort);
    ServerSocket ss = new ServerSocket(listenPort);

    Socket socket = ss.accept();
    log("Got socket connection");

    JobDetailImpl jobDetail = new JobDetailImpl("testjob", null, SimpleJob.class);
    jobDetail.setDurability(true);

    SimpleTriggerImpl trigger = new SimpleTriggerImpl("trigger1", "group");
    trigger.setStartTime(new Date(System.currentTimeMillis() + 30000L));
    trigger.setJobName("testjob");

    scheduler.addJob(jobDetail, false);
    scheduler.scheduleJob(trigger);

    // not strictly necessary but this should make the newly added trigger get ACQUIRED locally
    Thread.sleep(5000L);

    log("Closing sockets");
    socket.close();
    ss.close();
  }
