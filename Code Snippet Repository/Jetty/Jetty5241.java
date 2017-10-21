    protected synchronized InputStream getInputStream(boolean resetConnection)
        throws IOException
    {
        if (!checkConnection())
            throw new IOException( "Invalid resource");

        try
        {    
            if( _in != null)
            {
                InputStream in = _in;
                _in=null;
                return in;
            }
            return _connection.getInputStream();
        }
        finally
        {
            if (resetConnection)
            {
                _connection=null;
                if (LOG.isDebugEnabled()) LOG.debug("Connection nulled");
            }
        }
    }
