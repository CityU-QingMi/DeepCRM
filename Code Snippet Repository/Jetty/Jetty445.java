    private List<Callback> drain()
    {
        List<Callback> callbacks = new ArrayList<>();
        synchronized (lock)
        {
            while (true)
            {
                DeferredContentProvider.Chunk chunk = chunks.peek();
                if (chunk == null || chunk == EOF)
                    break;
                callbacks.add(chunk.callback);
                chunks.poll();
            }
        }
        return callbacks;
    }
