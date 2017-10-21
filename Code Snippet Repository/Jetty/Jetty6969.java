    @Override
    protected void doStart() throws Exception
    {
        if(this.objectFactory == null && context != null)
        {
            this.objectFactory = (DecoratedObjectFactory) context.getAttribute(DecoratedObjectFactory.ATTR);
            if (this.objectFactory == null)
            {
                throw new IllegalStateException("Unable to find required ServletContext attribute: " + DecoratedObjectFactory.ATTR);
            }
        }
    
        if(this.executor == null && context != null)
        {
            ContextHandler contextHandler = ContextHandler.getContextHandler(context);
            this.executor = contextHandler.getServer().getThreadPool();
        }
        
        Objects.requireNonNull(this.objectFactory, DecoratedObjectFactory.class.getName());
        Objects.requireNonNull(this.executor, Executor.class.getName());
        
        super.doStart();
    }
