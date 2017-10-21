    public void stop(BundleContext context) throws Exception
    {
        try
        {
            if (_reg != null)
            {
                _reg.unregister();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
