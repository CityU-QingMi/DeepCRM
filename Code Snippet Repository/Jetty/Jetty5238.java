    @Override
    public synchronized void close()
    {
        if (_in!=null)
        {
            try{_in.close();}catch(IOException e){LOG.ignore(e);}
            _in=null;
        }

        if (_connection!=null)
            _connection=null;
    }
