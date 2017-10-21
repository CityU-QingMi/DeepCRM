    private void notifyContent(Request.ContentListener listener, Request request, ByteBuffer content)
    {
        try
        {
            listener.onContent(request, content);
        }
        catch (Throwable x)
        {
            LOG.info("Exception while notifying listener " + listener, x);
        }
    }
