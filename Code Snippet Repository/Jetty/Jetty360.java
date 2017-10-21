    private void notifyFailure(Request.FailureListener listener, Request request, Throwable failure)
    {
        try
        {
            listener.onFailure(request, failure);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
