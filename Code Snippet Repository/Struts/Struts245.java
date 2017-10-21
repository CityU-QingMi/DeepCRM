  void start() {
    Thread thread = new Thread("FinalizableReferenceQueue") {
      @Override
      public void run() {
        while (true) {
          try {
            cleanUp(remove());
          } catch (InterruptedException e) { /* ignore */ }
        }
      }
    };
    thread.setDaemon(true);
    thread.start();
  }
