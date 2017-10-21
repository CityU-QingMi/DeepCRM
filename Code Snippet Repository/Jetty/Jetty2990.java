    @Override
    protected void doStart() throws Exception
    {
        super.doStart();

        if (getAcceptors()==0)
        {
            _acceptChannel.configureBlocking(false);
            _acceptor.set(_manager.acceptor(_acceptChannel));
        }
    }
