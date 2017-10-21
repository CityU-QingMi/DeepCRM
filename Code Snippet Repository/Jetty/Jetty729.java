    @Override
    public boolean isSuspended()
    {
        if (_request.isAsyncStarted())
            return true;
        try
        {
            return _request.getAsyncContext()!=null;
        }
        catch(IllegalStateException e)
        {
            // ignored
        }
        return false;
    }
