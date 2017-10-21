    @Override
    protected void doStart() throws Exception
    {
        if(_defaultProtocol==null)
            throw new IllegalStateException("No default protocol for "+this);
        _defaultConnectionFactory = getConnectionFactory(_defaultProtocol);
        if(_defaultConnectionFactory==null)
            throw new IllegalStateException("No protocol factory for default protocol '"+_defaultProtocol+"' in "+this);
        SslConnectionFactory ssl = getConnectionFactory(SslConnectionFactory.class);
        if (ssl != null)
        {
            String next = ssl.getNextProtocol();
            ConnectionFactory cf = getConnectionFactory(next);
            if (cf == null)
                throw new IllegalStateException("No protocol factory for SSL next protocol: '" + next + "' in " + this);
        }

        lease = ThreadBudget.leaseFrom(getExecutor(),this,_acceptors.length);
        super.doStart();

        _stopping=new CountDownLatch(_acceptors.length);
        for (int i = 0; i < _acceptors.length; i++)
        {
            Acceptor a = new Acceptor(i);
            addBean(a);
            getExecutor().execute(a);
        }

        LOG.info("Started {}", this);
    }
