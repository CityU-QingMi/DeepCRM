    @Override
    public void run()
    {
        final AtomicInteger m = new AtomicInteger();

        try
        {
            while (m.get() < messageCount)
            {
                conn.write(new TextFrame().setPayload(message));

                m.incrementAndGet();

                if (slowness > 0)
                {
                    TimeUnit.MILLISECONDS.sleep(slowness);
                }
            }
        }
        catch (InterruptedException | IOException e)
        {
            LOG.warn(e);
        }
    }
