  private void findMBean() {
    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    Set<ObjectName> beans = mbs.queryNames(null, null);

    boolean found = false;
    for (ObjectName on : beans) {
      if (on.getDomain().equals("quartz")) {
        found = true;
        System.err.println(on);
      }
    }

    if (!found) { throw new AssertionError("Cannot find bean in set: " + beans); }

    // quartz:type=QuartzScheduler,name=DefaultQuartzScheduler,instance=TERRACOTTA_CLUSTERED_SCHEDULER,node=d4ebfa742e544ba2aef17de1d5deede8
  }
