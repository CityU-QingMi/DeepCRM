    @Override
    protected void stopContext() throws Exception
    {
        stopWebapp();
        try
        {
            for (int i=_configurations.size();i-->0;)
                _configurations.get(i).deconfigure(this);

            if (_metadata != null)
                _metadata.clear();
            _metadata=new MetaData();

        }
        finally
        {
            if (_ownClassLoader)
            {
                ClassLoader loader = getClassLoader();
                if (loader != null && loader instanceof URLClassLoader)
                    ((URLClassLoader)loader).close(); 
                setClassLoader(null);
            }

            setAvailable(true);
            _unavailableException=null;
        }
    }
