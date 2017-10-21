    public boolean serviceRemoved (ServiceReference serviceRef, ContextHandler context)
    {

        if (context == null || serviceRef == null)
            return false;
        
        String watermark = (String)serviceRef.getProperty(OSGiWebappConstants.WATERMARK);
        if (watermark != null && !"".equals(watermark))
            return false;  //this service represents a contexthandler that will be deregistered as a service by another of our deployers
        
        App app = _serviceMap.remove(serviceRef);
        if (app != null)
        {
            getDeploymentManager().removeApp(app);
            return true;
        }

        return false;
    }
