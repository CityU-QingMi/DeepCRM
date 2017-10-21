    @Override
    public void removedService(ServiceReference reference, Object service)
    {
        if (service instanceof ServerInstanceWrapper)
        {
            try
            {
                ServerInstanceWrapper wrapper = (ServerInstanceWrapper)service;
                wrapper.stop();
                LOG.info("Stopped Server {}",wrapper.getManagedServerName());
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
        }
        
    }
