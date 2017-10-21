    public ServletContextHandler(HandlerContainer parent, String contextPath, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler,int options)
    {
        super((ContextHandler.Context)null);
        _options=options;
        _scontext = new Context();
        _sessionHandler = sessionHandler;
        _securityHandler = securityHandler;
        _servletHandler = servletHandler;
        
        _objFactory = new DecoratedObjectFactory();
        _objFactory.addDecorator(new DeprecationWarning());

        if (contextPath!=null)
            setContextPath(contextPath);
        
        if (parent instanceof HandlerWrapper)
            ((HandlerWrapper)parent).setHandler(this);
        else if (parent instanceof HandlerCollection)
            ((HandlerCollection)parent).addHandler(this);
        
        
        // Link the handlers
        relinkHandlers();
        
        if (errorHandler!=null)
            setErrorHandler(errorHandler);
    }
