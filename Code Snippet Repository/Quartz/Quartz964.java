  @Override
  protected void test(Scheduler scheduler) throws Throwable {
    Socket s = null;

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
  }
