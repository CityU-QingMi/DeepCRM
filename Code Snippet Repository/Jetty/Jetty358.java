    private void notifySuccess(Request.SuccessListener listener, Request request)
    {
        try
        {
            listener.onSuccess(request);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
