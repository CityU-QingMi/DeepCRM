        @Override
        public void lifeCycleStarted(LifeCycle event)
        {
            if (event == container)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("starting websocket container [{}]",event);
                }
                wsScope.instance.begin();
                return;
            }
            
            if (event instanceof WebSocketSessionScope)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("starting websocket session [{}]",event);
                }
                wsScope.instance.setSession((Session)event);
                return;
            }
        }
