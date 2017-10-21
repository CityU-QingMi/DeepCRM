    public void stop()
    {
        INSTANCE = null;
        for (BundleActivator fragAct : _activatedFragments)
        {
            try
            {
                fragAct.stop(_context);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
