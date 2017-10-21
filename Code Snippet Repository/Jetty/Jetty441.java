    @Override
    public void onFailure(Response response, Throwable failure)
    {
        List<Callback> callbacks;
        synchronized (lock)
        {
            if (this.failure != null)
                return;
            this.failure = failure;
            callbacks = drain();
            lock.notifyAll();
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Content failure", failure);

        callbacks.forEach(callback -> callback.failed(failure));
    }
