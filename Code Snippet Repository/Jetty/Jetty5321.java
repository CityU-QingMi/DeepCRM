    public boolean tryExecute(Runnable task)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} tryExecute {}",this ,task);

        if (task==null)
            return false;

        ReservedThread thread = _stack.pop();
        if (thread==null && task!=STOP)
        {
            startReservedThread();
            return false;
        }

        int size = _size.decrementAndGet();
        thread.offer(task);

        if (size==0 && task!=STOP)
            startReservedThread();

        return true;
    }
