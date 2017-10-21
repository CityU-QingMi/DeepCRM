    private boolean startThreads(int threadsToStart)
    {
        while (threadsToStart > 0 && isRunning())
        {
            int threads = _threadsStarted.get();
            if (threads >= _maxThreads)
                return false;

            if (!_threadsStarted.compareAndSet(threads, threads + 1))
                continue;

            boolean started = false;
            try
            {
                Thread thread = newThread(_runnable);
                thread.setDaemon(isDaemon());
                thread.setPriority(getThreadsPriority());
                thread.setName(_name + "-" + thread.getId());
                _threads.add(thread);

                thread.start();
                started = true;
                --threadsToStart;
            }
            finally
            {
                if (!started)
                    _threadsStarted.decrementAndGet();
            }
        }
        return true;
    }
