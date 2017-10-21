    public void setHandler(Handler handler)
    {
        try
        {
            Server server = getServer();
            if (handler!=null)
                handler.setServer(server);
            updateBean(_handler,handler,true);
            _handler=handler;

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
