    @Override
    public Object addingService(ServiceReference sr)
    {
        Bundle contributor = sr.getBundle();
        Server server = (Server) contributor.getBundleContext().getService(sr);
        String name = (String) sr.getProperty(OSGiServerConstants.MANAGED_JETTY_SERVER_NAME);
        if (name == null) { throw new IllegalArgumentException("The property " + OSGiServerConstants.MANAGED_JETTY_SERVER_NAME + " is mandatory"); }
        if (LOG.isDebugEnabled()) LOG.debug("Adding Server {}", name);
        ServerInstanceWrapper wrapper = new ServerInstanceWrapper(name);
        Properties props = new Properties();
        for (String key : sr.getPropertyKeys())
        {
            Object value = sr.getProperty(key);
            props.put(key, value);
        }
        try
        {
            wrapper.start(server, props);
            LOG.info("Started Server {}", name);
            return wrapper;
        }
        catch (Exception e)
        {
            LOG.warn(e);
            return sr.getBundle().getBundleContext().getService(sr);
        }
    }
