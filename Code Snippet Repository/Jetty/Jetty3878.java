    public void insertHandler(HandlerWrapper handler)
    {
        if (handler instanceof SessionHandler)
            setSessionHandler((SessionHandler)handler);
        else if (handler instanceof SecurityHandler)
            setSecurityHandler((SecurityHandler)handler);
        else if (handler instanceof GzipHandler)
            setGzipHandler((GzipHandler)handler);
        else if (handler instanceof ServletHandler)
            setServletHandler((ServletHandler)handler);
        else
        {
            HandlerWrapper tail = handler;
            while(tail.getHandler() instanceof HandlerWrapper)
                tail=(HandlerWrapper)tail.getHandler();
            if (tail.getHandler()!=null)
                throw new IllegalArgumentException("bad tail of inserted wrapper chain");
            
            // Skip any injected handlers
            HandlerWrapper h=this;
            while (h.getHandler() instanceof HandlerWrapper)
            {
                HandlerWrapper wrapper = (HandlerWrapper)h.getHandler();
                if (wrapper instanceof SessionHandler ||
                        wrapper instanceof SecurityHandler ||
                        wrapper instanceof ServletHandler)
                    break;
                h=wrapper;
            }
            
            Handler next=h.getHandler();
            doSetHandler(h,handler);
            doSetHandler(tail,next);
        }
        relinkHandlers();
    }
