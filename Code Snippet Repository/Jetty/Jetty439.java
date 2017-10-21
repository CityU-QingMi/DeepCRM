    @Override
    public void onContent(Response response, ByteBuffer content, Callback callback)
    {
        if (content.remaining() == 0)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Skipped empty content {}", content);
            callback.succeeded();
            return;
        }

        boolean closed;
        synchronized (lock)
        {
            closed = this.closed;
            if (!closed)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Queueing content {}", content);
                chunks.add(new DeferredContentProvider.Chunk(content, callback));
                lock.notifyAll();
            }
        }

        if (closed)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("InputStream closed, ignored content {}", content);
            callback.failed(new AsynchronousCloseException());
        }
    }
