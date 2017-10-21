    protected void exitScope(Request request)
    {
        if (!_contextListeners.isEmpty())
        {
            for (int i = _contextListeners.size(); i-->0;)
            {
                try
                {
                    _contextListeners.get(i).exitScope(_scontext,request);
                }
                catch(Throwable e)
                {
                    LOG.warn(e);
                }
            }
        }
    }
