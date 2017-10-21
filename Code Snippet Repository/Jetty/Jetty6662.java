    @Override
    public void close() throws IOException
    {
        try
        {
            flush(true);
            bufferPool.release(buffer);
            if (LOG.isDebugEnabled())
                LOG.debug("Stream closed, {} frames sent", frameCount);
            // Notify without holding locks.
            notifySuccess();
        }
        catch (Throwable x)
        {
            // Notify without holding locks.
            notifyFailure(x);
            throw x;
        }
    }
