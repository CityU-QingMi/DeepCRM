    @Override
    public void run()
    {
        final AtomicInteger m = new AtomicInteger();

        try
        {
            LOG.debug("Writing {} messages to connection {}",messageCount);
            LOG.debug("Artificial Slowness {} ms",slowness);
            Future<Void> lastMessage = null;
            RemoteEndpoint remote = session.getRemote();
            while (m.get() < messageCount)
            {
                lastMessage = remote.sendStringByFuture(message + "/" + m.get() + "/");

                m.incrementAndGet();

                if (slowness > 0)
                {
                    TimeUnit.MILLISECONDS.sleep(slowness);
                }
            }
            if (remote.getBatchMode() == BatchMode.ON)
                remote.flush();
            // block on write of last message
            if (lastMessage != null)
                lastMessage.get(2,TimeUnit.MINUTES); // block on write
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
