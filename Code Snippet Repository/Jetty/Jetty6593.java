    @Override
    public void outgoingFrame(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        // We use a queue and an IteratingCallback to handle concurrency.
        // We must compress and write atomically, otherwise the compression
        // context on the other end gets confused.

        if (flusher.isFailed())
        {
            notifyCallbackFailure(callback,new ZipException());
            return;
        }

        FrameEntry entry = new FrameEntry(frame,callback,batchMode);
        if (LOG.isDebugEnabled())
            LOG.debug("Queuing {}",entry);
        offerEntry(entry);
        flusher.iterate();
    }
