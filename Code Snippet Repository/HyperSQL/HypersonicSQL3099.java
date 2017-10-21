    public ServerSocket createServerSocket(int port) throws Exception {

        SSLServerSocket ss;

        ss = (SSLServerSocket) getServerSocketFactoryImpl().createServerSocket(
            port);

        if (Error.TRACESYSTEMOUT) {
            Error.printSystemOut("[" + this + "]: createServerSocket()");
            Error.printSystemOut("capabilities for " + ss + ":");
            Error.printSystemOut("----------------------------");
            dump("supported cipher suites", ss.getSupportedCipherSuites());
            dump("enabled cipher suites", ss.getEnabledCipherSuites());
        }

        return ss;
    }
