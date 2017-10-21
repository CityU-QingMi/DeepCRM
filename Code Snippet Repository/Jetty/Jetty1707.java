    public void write(Callback callback, ByteBuffer... buffers) throws WritePendingException
    {
        if (DEBUG)
            LOG.debug("write: {} {}", this, BufferUtil.toDetailString(buffers));

        if (!updateState(__IDLE,__WRITING))
            throw new WritePendingException();

        try
        {
            buffers=flush(buffers);

            // if we are incomplete?
            if (buffers!=null)
            {
                if (DEBUG)
                    LOG.debug("flushed incomplete");
                PendingState pending=new PendingState(buffers, callback);
                if (updateState(__WRITING,pending))
                    onIncompleteFlush();
                else
                    fail(pending);
                return;
            }

            // If updateState didn't succeed, we don't care as our buffers have been written
            if (!updateState(__WRITING,__IDLE))
                ignoreFail();
            if (callback!=null)
                callback.succeeded();
        }
        catch (IOException e)
        {
            if (DEBUG)
                LOG.debug("write exception", e);
            if (updateState(__WRITING,__IDLE))
            {
                if (callback!=null)
                    callback.failed(e);
            }
            else
                fail(new PendingState(buffers, callback));
        }
    }
