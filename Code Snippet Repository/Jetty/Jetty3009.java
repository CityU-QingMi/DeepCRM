    @Override
    public void setServer(Server server)
    {
        if (server==getServer())
            return;
        
        if (isStarted())
            throw new IllegalStateException(STARTED);

        super.setServer(server);
        Handler[] handlers=getHandlers();
        if (handlers!=null)
            for (Handler h : handlers)
                h.setServer(server);
    }
