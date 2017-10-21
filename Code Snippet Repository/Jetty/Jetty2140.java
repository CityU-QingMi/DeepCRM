    public void processBinding(Node node, App app) throws Exception
    {
        //TODO  how to NOT send this event if its not a webapp: 
        //OSGi Enterprise Spec only wants an event sent if its a webapp bundle (ie not a ContextHandler)
        if (!(app instanceof AbstractOSGiApp))
        {
           doProcessBinding(node,app);
        }
        else
        {
            EventSender.getInstance().send(EventSender.DEPLOYING_EVENT, ((AbstractOSGiApp)app).getBundle(), app.getContextPath());
            try
            {
                doProcessBinding(node,app);
                ((AbstractOSGiApp)app).registerAsOSGiService();
                EventSender.getInstance().send(EventSender.DEPLOYED_EVENT, ((AbstractOSGiApp)app).getBundle(), app.getContextPath());
            }
            catch (Exception e)
            {
                EventSender.getInstance().send(EventSender.FAILED_EVENT, ((AbstractOSGiApp)app).getBundle(), app.getContextPath()); 
                throw e;
            }
        }
    }
