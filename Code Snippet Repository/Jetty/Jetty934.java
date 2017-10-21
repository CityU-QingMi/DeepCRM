    @Override
    public void join() throws InterruptedException
    {
        final Executor executor=_executor;
        if (executor instanceof ThreadPool)
            ((ThreadPool)executor).join();
        else if (executor instanceof ExecutorService)
            ((ExecutorService)executor).awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        else
            throw new IllegalStateException();
    }
