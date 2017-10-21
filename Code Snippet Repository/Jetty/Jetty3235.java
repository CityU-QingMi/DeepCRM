    public void complete (Session session, Request request)
    {
        if (request.isAsyncStarted() && request.getDispatcherType() == DispatcherType.REQUEST)
        {
            request.getAsyncContext().addListener(new SessionAsyncListener(session));
        }
        else
        {
            complete(session);
        }
        //if dispatcher type is not async and not request, complete immediately (its a forward or an include)
        
        //else if dispatcher type is request and not async, complete immediately
        
        //else register an async callback completion listener that will complete the session
    }
