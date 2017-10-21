    @OnWebSocketConnect
    public void onOpen(Session session)
    {
        this.session = session;
        remoteLock.lock();
        try
        {
            this.remote = session.getRemote();
        }
        finally
        {
            remoteLock.unlock();
        }
    }
