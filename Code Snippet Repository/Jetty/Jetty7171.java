    @Override
    public void onOpen(Session session)
    {
        if (keepAlive == null)
        {
            keepAlive = new KeepAlive(session);
        }
        keepAlive.start();
        super.onOpen(session);
    }
