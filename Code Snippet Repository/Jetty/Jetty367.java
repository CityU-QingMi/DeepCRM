    private void notifyCommit(Request.CommitListener listener, Request request)
    {
        try
        {
            listener.onCommit(request);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
