    private static Runnable poll(Queue<Runnable> queue)
    {
        int loops = 0;
        while (true)
        {
            Runnable element = queue.poll();
            if (element != null)
                return element;
            // Busy loop
            sleepMicros(1);
            ++loops;
            if (loops % 16 == 0)
                logger.warn("Spin looping while polling empty queue: {} spins: ", loops);
        }
    }
