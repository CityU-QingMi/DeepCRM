    public void setHandlers(Handler[] handlers)
    {
        if (!_mutableWhenRunning && isStarted())
            throw new IllegalStateException(STARTED);

        if (handlers!=null)
        {
            // check for loops
            for (Handler handler:handlers)
                if (handler == this || (handler instanceof HandlerContainer &&
                    Arrays.asList(((HandlerContainer)handler).getChildHandlers()).contains(this)))
                        throw new IllegalStateException("setHandler loop");
          
            // Set server
            for (Handler handler:handlers)
                if (handler.getServer()!=getServer())
                    handler.setServer(getServer());
        }
        Handler[] old=_handlers;;
        _handlers = handlers;
        updateBeans(old, handlers);
    }
