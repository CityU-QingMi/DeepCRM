    public PerMessageDeflateExtensionTest(String mode, boolean sslMode, String scheme, TestCaseMessageSize msgSize, int bufferSize) throws Exception
    {
        server = new SimpleServletServer(new EchoServlet());
        server.enableSsl(sslMode);
        server.start();

        this.scheme = scheme;
        this.msgSize = msgSize.size;
        this.inputBufferSize = bufferSize;
    }
