    public Result terminateRequest()
    {
        Result result = null;
        synchronized (this)
        {
            if (requestState == State.COMPLETED)
                requestState = State.TERMINATED;
            if (requestState == State.TERMINATED && responseState == State.TERMINATED)
                result = new Result(getRequest(), requestFailure, getResponse(), responseFailure);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Terminated request for {}, result: {}", this, result);

        return result;
    }
