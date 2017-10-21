        @Override
        public void lifeCycleStopped(LifeCycle event)
        {
            if (event == container)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("stopped websocket container [{}]",event);
                }
                this.wsScope.instance.end();
                this.wsScope.instance.destroy();
                this.wsScope.destroy();
            }
        }
