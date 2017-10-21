  private static Collection<ThreadInfo> getThreadDump() {
    Collection<ThreadInfo> dump = new ArrayList<ThreadInfo>();
    ThreadMXBean tbean = ManagementFactory.getThreadMXBean();
    for (ThreadInfo tinfo : tbean.getThreadInfo(tbean.getAllThreadIds(), Integer.MAX_VALUE)) {
      if (tinfo != null) {
        dump.add(tinfo);
      }
    }
    return dump;
  }
