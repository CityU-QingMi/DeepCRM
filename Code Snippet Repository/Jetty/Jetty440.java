    @Override
    public void onSuccess(Response response)
    {
        synchronized (lock)
        {
            if (!closed)
                chunks.add(EOF);
            lock.notifyAll();
        }

        if (LOG.isDebugEnabled())
            LOG.debug("End of content");
    }
