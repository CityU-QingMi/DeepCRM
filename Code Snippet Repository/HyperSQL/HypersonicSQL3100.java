    public ServerSocket createServerSocket(int port,
                                           String address) throws Exception {

        SSLServerSocket ss;
        InetAddress     addr;

        addr = InetAddress.getByName(address);
        ss = (SSLServerSocket) getServerSocketFactoryImpl().createServerSocket(
            port, 128, addr);

        if (Error.TRACESYSTEMOUT) {
            Error.printSystemOut("[" + this + "]: createServerSocket()");
            Error.printSystemOut("capabilities for " + ss + ":");
            Error.printSystemOut("----------------------------");
            dump("supported cipher suites", ss.getSupportedCipherSuites());
            dump("enabled cipher suites", ss.getEnabledCipherSuites());
        }

        return ss;
    }
