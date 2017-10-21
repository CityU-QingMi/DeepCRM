    public void close()
    {
        if (closed.compareAndSet(false,true))
        {
            LOG.debug("{} closing {}",this);
            EOFException eof = new EOFException("Connection has been closed locally");
            flusher.failed(eof);

            // Fail also queued entries.
            List<FrameEntry> entries = new ArrayList<>();
            synchronized (lock)
            {
                entries.addAll(queue);
                queue.clear();
            }
            // Notify outside sync block.
            for (FrameEntry entry : entries)
            {
                notifyCallbackFailure(entry.callback,eof);
            }
        }
    }
