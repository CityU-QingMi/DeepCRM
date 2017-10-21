    @Override
    public void send(Response.CompleteListener listener)
    {
        TimeoutCompleteListener timeoutListener = null;
        try
        {
            if (getTimeout() > 0)
            {
                timeoutListener = new TimeoutCompleteListener(this);
                timeoutListener.schedule(client.getScheduler());
                responseListeners.add(timeoutListener);
            }
            send(this, listener);
        }
        catch (Throwable x)
        {
            // Do not leak the scheduler task if we
            // can't even start sending the request.
            if (timeoutListener != null)
                timeoutListener.cancel();
            throw x;
        }
    }
