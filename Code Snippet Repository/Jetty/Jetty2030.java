    public Integer getHttpPort() 
    {       
        Collection<ObjectName> connectors = null;
        MBeanServerConnection service;
        try
        {
            service = JMXMonitor.getServiceConnection();

            connectors = service.queryNames(new ObjectName("org.eclipse.jetty.nio:type=selectchannelconnector,*"), null);
            if (connectors != null && connectors.size() > 0)
            {
                Integer lowest = Integer.MAX_VALUE;
                for (final ObjectName connector : connectors) {
                    lowest = (Integer)service.getAttribute(connector, "port");
                }
        
                if (lowest < Integer.MAX_VALUE)
                    return lowest;
            }
        }
        catch (Exception ex)
        {
            LOG.debug(ex);
        }
        
        return 0;
    }
