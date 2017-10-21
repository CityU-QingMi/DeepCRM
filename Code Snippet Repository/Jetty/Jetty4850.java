    @Override
    public void succeeded()
    {
        // Forward success on the last success.
        while (true)
        {
            int current = count.get();

            // Already completed ?
            if (current == 0)
                return;

            if (count.compareAndSet(current, current - 1))
            {
                if (current == 1)
                    super.succeeded();
                return;
            }
        }
    }
