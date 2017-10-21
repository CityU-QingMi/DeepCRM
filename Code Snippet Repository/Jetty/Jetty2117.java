    public void registerAsOSGiService() throws Exception
    {
        if (_registration == null)
        {
            Dictionary<String,String> properties = new Hashtable<String,String>();
            properties.put(OSGiWebappConstants.WATERMARK, OSGiWebappConstants.WATERMARK);
            if (getBundleSymbolicName() != null)
                properties.put(OSGiWebappConstants.OSGI_WEB_SYMBOLICNAME, getBundleSymbolicName());
            if (getBundleVersionAsString() != null)
                properties.put(OSGiWebappConstants.OSGI_WEB_VERSION, getBundleVersionAsString());
            properties.put(OSGiWebappConstants.OSGI_WEB_CONTEXTPATH, getContextPath());
            ServiceRegistration rego = FrameworkUtil.getBundle(this.getClass()).getBundleContext().registerService(ContextHandler.class.getName(), getContextHandler(), properties);
            setRegistration(rego);
        }
    }
