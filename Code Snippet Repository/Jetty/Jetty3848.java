    @SuppressWarnings("")
    @Override
    public void doStart()
        throws Exception
    {
        //if no class already loaded and no classname, make permanently unavailable
        if (_class==null && (_className==null || _className.equals("")))
            throw new UnavailableException("No class in holder "+toString());
        
        //try to load class
        if (_class==null)
        {
            try
            {
                _class=Loader.loadClass(_className);
                if(LOG.isDebugEnabled())
                    LOG.debug("Holding {} from {}",_class,_class.getClassLoader());
            }
            catch (Exception e)
            {
                LOG.warn(e);
                throw new UnavailableException("Class loading error for holder "+toString());
            }
        }
    }
