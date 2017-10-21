    protected void enterScope(Request request, Object reason)
    {
        if (!_contextListeners.isEmpty())
        {
            for (ContextScopeListener listener:_contextListeners)
            {
                try
                {
                    listener.enterScope(_scontext,request,reason);
                }
                catch(Throwable e)
                {
                    LOG.warn(e);
                }
            }
        }
    }
