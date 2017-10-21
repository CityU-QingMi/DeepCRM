    @Override
    public boolean exists()
    {
        try
        {
            synchronized(this)
            {
                if (checkConnection() && _in==null )
                    _in = _connection.getInputStream();
            }
        }
        catch (IOException e)
        {
            LOG.ignore(e);
        }
        return _in!=null;
    }
