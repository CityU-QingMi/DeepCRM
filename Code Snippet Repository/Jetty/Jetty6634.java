    public void enqueue(Frame frame, WriteCallback callback, BatchMode batchMode)
    {
        if (closed.get())
        {
            notifyCallbackFailure(callback,new EOFException("Connection has been closed locally"));
            return;
        }
        if (flusher.isFailed())
        {
            notifyCallbackFailure(callback,failure);
            return;
        }

        FrameEntry entry = new FrameEntry(frame,callback,batchMode);

        synchronized (lock)
        {
            switch (frame.getOpCode())
            {
                case OpCode.PING:
                {
                    // Prepend PINGs so they are processed first.
                    queue.offerFirst(entry);
                    break;
                }
                case OpCode.CLOSE:
                {
                    // There may be a chance that other frames are
                    // added after this close frame, but we will
                    // fail them later to keep it simple here.
                    closed.set(true);
                    queue.offer(entry);
                    break;
                }
                default:
                {
                    queue.offer(entry);
                    break;
                }
            }
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug("{} queued {}",this,entry);
        }

        flusher.iterate();
    }
