    public void setHandler(Handler handler)
    {
        if (isStarted())
            throw new IllegalStateException(STARTED);

        // check for loops
        if (handler==this || (handler instanceof HandlerContainer &&
            Arrays.asList(((HandlerContainer)handler).getChildHandlers()).contains(this)))
            throw new IllegalStateException("setHandler loop");
        
        if (handler!=null)
            handler.setServer(getServer());
        
        Handler old=_handler;
        _handler=handler;
        updateBean(old,_handler,true);
    }
