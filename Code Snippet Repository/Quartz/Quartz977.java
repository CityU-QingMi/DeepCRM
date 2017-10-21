  private static void threadDump(final Throwable e) {
    System.out.println("Uncaught exception");
    System.out.println("----------------------------");
    e.printStackTrace(System.out);

    System.out.println("Generating Thread-dump at:" + (new java.util.Date()).toString());
    System.out.println("----------------------------");
    Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
    for (Thread t : map.keySet()) {
      StackTraceElement[] elem = map.get(t);
      System.out.print("\"" + t.getName() + "\"");
      System.out.print(" prio=" + t.getPriority());
      System.out.print(" tid=" + t.getId());
      Thread.State s = t.getState();
      String state = s.name();
      System.out.println(" @@@@ " + state);
      for (StackTraceElement anElem : elem) {
        System.out.print("  at ");
        System.out.println(anElem.toString());
      }
      System.out.println("----------------------------");
    }
  }
