    @Override
    public void lifeCycleStarting(LifeCycle event)
    {
        if (event instanceof WebSocketContainerScope)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("started websocket container [{}]",event);
            }
            ContainerListener listener = new ContainerListener((WebSocketContainerScope)event);
            if (event instanceof ContainerLifeCycle)
            {
                ContainerLifeCycle container = (ContainerLifeCycle)event;
                container.addLifeCycleListener(listener);
                container.addEventListener(listener);
            }
            else
            {
                throw new RuntimeException("Unable to setup CDI against non-container: " + event.getClass().getName());
            }
        }
    }
