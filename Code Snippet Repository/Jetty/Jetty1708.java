    public void completeWrite()
    {
        if (DEBUG)
            LOG.debug("completeWrite: {}", this);

        State previous = _state.get();

        if (previous.getType()!=StateType.PENDING)
            return; // failure already handled.

        PendingState pending = (PendingState)previous;
        if (!updateState(pending,__COMPLETING))
            return; // failure already handled.

        try
        {
            ByteBuffer[] buffers = pending.getBuffers();

            buffers=flush(buffers);

            // if we are incomplete?
            if (buffers!=null)
            {
                if (DEBUG)
                    LOG.debug("flushed incomplete {}",BufferUtil.toDetailString(buffers));
                if (buffers!=pending.getBuffers())
                    pending=new PendingState(buffers, pending._callback);
                if (updateState(__COMPLETING,pending))
                    onIncompleteFlush();
                else
                    fail(pending);
                return;
            }

            // If updateState didn't succeed, we don't care as our buffers have been written
            if (!updateState(__COMPLETING,__IDLE))
                ignoreFail();
            pending.complete();
        }
        catch (IOException e)
        {
            if (DEBUG)
                LOG.debug("completeWrite exception", e);
            if(updateState(__COMPLETING,__IDLE))
                pending.fail(e);
            else
                fail(pending);
        }
    }
