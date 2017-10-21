    protected void failedCallback(final Callback callback, final Throwable x)
    {
        Runnable failCallback = () ->
        {
            try
            {
                callback.failed(x);
            }
            catch (Exception e)
            {
                LOG.warn(e);
            }
        };
        
        switch(Invocable.getInvocationType(callback))
        {
            case BLOCKING:
                try
                {
                    getExecutor().execute(failCallback); 
                }
                catch(RejectedExecutionException e)
                {
                    LOG.debug(e);
                    callback.failed(x);
                }
                break;
                
            case NON_BLOCKING:
                failCallback.run();
                break;
                
            case EITHER:
                Invocable.invokeNonBlocking(failCallback);

        }
    }
