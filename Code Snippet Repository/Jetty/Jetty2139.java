    public void stop(BundleContext context) throws Exception
    {
        try
        {
            if (_jettyServerServiceTracker != null)
            {
                _jettyServerServiceTracker.close();
                _jettyServerServiceTracker = null;
            }
            if (_packageAdminServiceTracker != null)
            {
                _packageAdminServiceTracker.stop();
                context.removeServiceListener(_packageAdminServiceTracker);
                _packageAdminServiceTracker = null;
            }
            if (_registeredServer != null)
            {
                try
                {
                    _registeredServer.unregister();
                }
                catch (IllegalArgumentException ill)
                {
                    // already unregistered.
                }
                finally
                {
                    _registeredServer = null;
                }
            }
        }
        finally
        {
            INSTANCE = null;
        }
    }
