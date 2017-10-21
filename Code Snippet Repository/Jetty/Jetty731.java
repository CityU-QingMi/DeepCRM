    @Override
    public void undispatch()
    {
        if (isSuspended())
        {
            _initial=false;
            if (ContinuationFilter.__debug)
                throw new ContinuationThrowable();
            throw __exception;
        }
        throw new IllegalStateException("!suspended");
    }
