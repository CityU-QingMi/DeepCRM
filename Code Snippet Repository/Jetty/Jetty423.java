    @Override
    public void setListener(Listener listener)
    {
        if (!this.listener.compareAndSet(null, listener))
            throw new IllegalStateException(String.format("The same %s instance cannot be used in multiple requests",
                    AsyncContentProvider.class.getName()));

        if (isClosed())
        {
            synchronized (lock)
            {
                long total = 0;
                for (Chunk chunk : chunks)
                    total += chunk.buffer.remaining();
                length = total;
            }
        }
    }
