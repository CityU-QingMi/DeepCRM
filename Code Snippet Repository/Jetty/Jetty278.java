    public Result terminateResponse()
    {
        Result result = null;
        synchronized (this)
        {
            if (responseState == State.COMPLETED)
                responseState = State.TERMINATED;
            if (requestState == State.TERMINATED && responseState == State.TERMINATED)
                result = new Result(getRequest(), requestFailure, getResponse(), responseFailure);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Terminated response for {}, result: {}", this, result);

        return result;
    }
