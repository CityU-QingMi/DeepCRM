    public void insertHandler(HandlerWrapper wrapper)
    {
        if (wrapper==null)
            throw new IllegalArgumentException();
        
        HandlerWrapper tail = wrapper;
        while(tail.getHandler() instanceof HandlerWrapper)
            tail=(HandlerWrapper)tail.getHandler();
        if (tail.getHandler()!=null)
            throw new IllegalArgumentException("bad tail of inserted wrapper chain");
        
        Handler next=getHandler();
        setHandler(wrapper);
        tail.setHandler(next);
    }
