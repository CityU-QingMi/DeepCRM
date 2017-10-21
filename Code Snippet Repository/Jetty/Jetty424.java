    private boolean offer(Chunk chunk)
    {
        Throwable failure;
        boolean result = false;
        synchronized (lock)
        {
            failure = this.failure;
            if (failure == null)
            {
                result = chunks.offer(chunk);
                if (result && chunk != CLOSE)
                    ++size;
            }
        }
        if (failure != null)
            chunk.callback.failed(failure);
        else if (result)
            notifyListener();
        return result;
    }
