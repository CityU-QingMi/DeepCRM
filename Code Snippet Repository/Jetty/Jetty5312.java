    public QueuedThreadPool(@Name("maxThreads") int maxThreads, @Name("minThreads") int minThreads, @Name("idleTimeout") int idleTimeout, @Name("queue") BlockingQueue<Runnable> queue, @Name("threadGroup") ThreadGroup threadGroup)
    {
        setMinThreads(minThreads);
        setMaxThreads(maxThreads);
        setIdleTimeout(idleTimeout);
        setStopTimeout(5000);

        if (queue==null)
        {
            int capacity=Math.max(_minThreads, 8);
            queue=new BlockingArrayQueue<>(capacity, capacity);
        }
        _jobs=queue;
        _threadGroup=threadGroup;
        _budget=new ThreadBudget(this);
    }
