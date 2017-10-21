    @Override
    protected void startContext() throws Exception
    {
        ServletContainerInitializerCaller sciBean = getBean(ServletContainerInitializerCaller.class);
        if (sciBean!=null)
            sciBean.start();

        if (_servletHandler != null)
        {
            // Call decorators on all holders, and also on any EventListeners before
            // decorators are called on any other classes (like servlets and filters)
            if(_servletHandler.getListeners() != null)
            {
                for (ListenerHolder holder:_servletHandler.getListeners())
                {             
                    _objFactory.decorate(holder.getListener());
                }
            }
        }
        
        super.startContext();

        // OK to Initialize servlet handler now that all relevant object trees have been started
        if (_servletHandler != null)
            _servletHandler.initialize();
    }
