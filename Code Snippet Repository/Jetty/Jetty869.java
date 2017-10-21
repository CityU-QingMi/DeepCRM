    protected boolean onContent(ByteBuffer buffer)
    {
        try
        {
            return listener.onContent(getRequest(), streamType, buffer);
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Exception while invoking listener " + listener, x);
            return false;
        }
    }
