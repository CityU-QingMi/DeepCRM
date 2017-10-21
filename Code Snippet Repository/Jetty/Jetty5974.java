    @Override
    public void destroy()
    {
        // Prepare for configuration
        MultiException mx=new MultiException();
        if (_configurations!=null)
        {
            for (int i=_configurations.size();i-->0;)
            {
                try
                {
                    _configurations.get(i).destroy(this);
                }
                catch(Exception e)
                {
                    mx.add(e);
                }
            }
        }
        _configurations.clear();
        super.destroy();
        mx.ifExceptionThrowRuntime();
    }
