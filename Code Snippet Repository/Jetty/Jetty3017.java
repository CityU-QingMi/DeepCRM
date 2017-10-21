    protected void stopContext () throws Exception
    {
        //stop all the handler hierarchy
        super.doStop();

        //Call the context listeners
        ServletContextEvent event = new ServletContextEvent(_scontext);
        Collections.reverse(_destroySerletContextListeners);
        MultiException ex = new MultiException();
        for (ServletContextListener listener:_destroySerletContextListeners)
        {
            try
            {
                callContextDestroyed(listener,event);
            }
            catch(Exception x)
            {
                ex.add(x);
            }
        }
        ex.ifExceptionThrow();
    }
