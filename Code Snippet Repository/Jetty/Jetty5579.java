    private static void consume(Queue<Runnable> queue, int writers, boolean blocking)
    {
        while (true)
        {
            Runnable element = blocking ? take(queue) : poll(queue);
            if (element == END)
                if (--writers == 0)
                    break;
        }
    }
