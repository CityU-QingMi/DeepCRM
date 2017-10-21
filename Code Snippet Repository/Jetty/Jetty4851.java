    @Override
    public void failed(Throwable failure)
    {
        // Forward failure on the first failure.
        while (true)
        {
            int current = count.get();

            // Already completed ?
            if (current == 0)
                return;

            if (count.compareAndSet(current, 0))
            {
                super.failed(failure);
                return;
            }
        }
    }
