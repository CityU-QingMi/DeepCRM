    @Override
    public void close()
        throws IOException
    {
        synchronized(RolloverFileOutputStream.class)
        {
            try{super.close();}
            finally
            {
                out=null;
                _file=null;
            }
    
            if (_rollTask != null)
            {
                _rollTask.cancel();
            }
        }
    }
