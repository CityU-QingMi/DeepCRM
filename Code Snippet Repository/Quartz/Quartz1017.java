  private LocalLockState getLocalLockState() {
    LocalLockState rv = localStateLock;
    if (rv != null) return rv;

    synchronized (DefaultClusteredJobStore.class) {
      if (localStateLock == null) {
        localStateLock = new LocalLockState();
      }
      return localStateLock;
    }
  }
