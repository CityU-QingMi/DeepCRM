    public void processBinding(Node node, App app) throws Exception
    {
        EventSender.getInstance().send(EventSender.UNDEPLOYING_EVENT, ((AbstractOSGiApp)app).getBundle(), app.getContextPath());
        ClassLoader old = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(_server.getParentClassLoaderForWebapps());
        try
        {
            super.processBinding(node,app);
        }
        finally 
        {
            Thread.currentThread().setContextClassLoader(old);
        }
        EventSender.getInstance().send(EventSender.UNDEPLOYED_EVENT, ((AbstractOSGiApp)app).getBundle(), app.getContextPath());
        ((AbstractOSGiApp)app).deregisterAsOSGiService();
    }
