    private void loadClass ()
    {
        if (_clazz != null)
            return;
        
        if (_className == null)
            return;
        
        try
        {
            _clazz = Loader.loadClass(_className);
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }  
