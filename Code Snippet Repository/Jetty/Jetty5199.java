    @Override
    protected synchronized boolean checkConnection()
    {
        super.checkConnection();
        try
        {
            if (_jarConnection!=_connection)
                newConnection();
        }
        catch(IOException e)
        {
            LOG.ignore(e);
            _jarConnection=null;
        }
        
        return _jarConnection!=null;
    }
