  private static void removeKnownThreads(Collection<ThreadInfo> dump) {
    List<ThreadIgnore> ignores = Arrays.asList(new ThreadIgnore("http", "org.apache.tomcat."),
                                               new ThreadIgnore("Attach Listener"),
                                               new ThreadIgnore("Poller SunPKCS11", "sun.security.pkcs11."),
                                               new ThreadIgnore("(Attach Listener)"),
                                               new ThreadIgnore("JFR request timer"),
                                               new ThreadIgnore("JMAPI event thread"),
                                               new ThreadIgnore("AWT-AppKit"),
                                               new ThreadIgnore("Keep-Alive-SocketCleaner"));

    for (Iterator<ThreadInfo> it = dump.iterator(); it.hasNext();) {
      ThreadInfo threadInfo = it.next();
      for (ThreadIgnore ignore : ignores) {
        if (ignore.canIgnore(threadInfo)) {
          it.remove();
          break;
        }
      }
    }
  }
