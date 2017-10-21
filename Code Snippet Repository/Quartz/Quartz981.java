  private static void removeThreads(Collection<ThreadInfo> from, Collection<ThreadInfo> remove) {
    for (Iterator<ThreadInfo> it = from.iterator(); it.hasNext();) {
      ThreadInfo ti = it.next();
      for (ThreadInfo r : remove) {
        if (r.getThreadId() == ti.getThreadId()) {
          it.remove();
          break;
        }
      }
    }
  }
