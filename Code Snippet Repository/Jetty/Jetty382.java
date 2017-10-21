    private void notifyContent(Response.AsyncContentListener listener, Response response, ByteBuffer buffer, Callback callback)
    {
        try
        {
            listener.onContent(response, buffer, callback);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
