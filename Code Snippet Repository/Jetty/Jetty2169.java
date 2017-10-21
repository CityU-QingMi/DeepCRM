    public  void send (String topic, Bundle wab, String contextPath, Exception ex)
    {        
        EventAdmin service = (EventAdmin)_serviceTracker.getService();
        if (service != null) {
            Dictionary<String,Object> props = new Hashtable<String,Object>();
            props.put("bundle.symbolicName", wab.getSymbolicName());
            props.put("bundle.id", wab.getBundleId());
            props.put("bundle", wab);
            props.put("bundle.version", wab.getVersion());
            props.put("context.path", contextPath);
            props.put("timestamp", System.currentTimeMillis());
            props.put("extender.bundle", _myBundle);
            props.put("extender.bundle.symbolicName", _myBundle.getSymbolicName());
            props.put("extender.bundle.id", _myBundle.getBundleId());
            props.put("extender.bundle.version", _myBundle.getVersion());
            
            if (FAILED_EVENT.equalsIgnoreCase(topic)  && ex != null)
                props.put("exception", ex);
    
            service.sendEvent(new Event(topic, props));
        }
    }
