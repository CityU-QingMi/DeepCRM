    @Override
    public synchronized void close()
    {
        _exists=false;
        _list=null;
        _entry=null;
        _file=null;
        //if the jvm is not doing url caching, then the JarFiles will not be cached either,
        //and so they are safe to close
        if (!getUseCaches())
        {
            if ( _jarFile != null )
            {
                try
                {
                    if (LOG.isDebugEnabled())
                        LOG.debug("Closing JarFile "+_jarFile.getName());
                    _jarFile.close();
                }
                catch ( IOException ioe )
                {
                    LOG.ignore(ioe);
                }
            }
        }
        _jarFile=null;
        super.close();
    }
