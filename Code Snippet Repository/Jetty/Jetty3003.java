    @Override
    protected void doStart() throws Exception
    {
        super.doStart();

        SSLEngine engine = _sslContextFactory.newSSLEngine();
        engine.setUseClientMode(false);
        SSLSession session=engine.getSession();

        if (session.getPacketBufferSize()>getInputBufferSize())
            setInputBufferSize(session.getPacketBufferSize());
    }
