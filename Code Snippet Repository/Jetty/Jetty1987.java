    @Override
    protected void doStart() throws Exception
    {

        if (this.server == null)
            throw new IllegalStateException("Server not set for MavenServerConnector");

        this.delegate = new ServerConnector(this.server);
        this.delegate.setName(this.name);
        this.delegate.setPort(this.port);
        this.delegate.setHost(this.host);
        this.delegate.setIdleTimeout(idleTimeout);
        this.delegate.setSoLingerTime(lingerTime);
        this.delegate.start();

        super.doStart();
    }
