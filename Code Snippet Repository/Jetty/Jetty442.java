    @Override
    public void onComplete(Result result)
    {
        Throwable failure = result.getFailure();
        List<Callback> callbacks = Collections.emptyList();
        synchronized (lock)
        {
            this.result = result;
            if (result.isFailed() && this.failure == null)
            {
                this.failure = failure;
                callbacks = drain();
            }
            // Notify the response latch in case of request failures.
            responseLatch.countDown();
            resultLatch.countDown();
            lock.notifyAll();
        }

        if (LOG.isDebugEnabled())
        {
            if (failure == null)
                LOG.debug("Result success");
            else
                LOG.debug("Result failure", failure);
        }

        callbacks.forEach(callback -> callback.failed(failure));
    }
