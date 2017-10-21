    void await() throws InterruptedException
    {
        synchronized (this)
        {
            while (alive)
            {
                wait();
            }
        }
    }
