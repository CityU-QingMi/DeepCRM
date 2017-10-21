    public void awaitMessages(int expectedMessageCount, int timeoutDuration, TimeUnit timeoutUnit) throws TimeoutException
    {
        long msDur = TimeUnit.MILLISECONDS.convert(timeoutDuration,timeoutUnit);
        long now = System.currentTimeMillis();
        long expireOn = now + msDur;
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Await Message.. Now: {} - expireOn: {} ({} ms)",now,expireOn,msDur);
        }

        while (this.size() < expectedMessageCount)
        {
            try
            {
                TimeUnit.MILLISECONDS.sleep(20);
            }
            catch (InterruptedException gnore)
            {
                /* ignore */
            }
            if (!LOG.isDebugEnabled() && (System.currentTimeMillis() > expireOn))
            {
                throw new TimeoutException(String.format("Timeout reading all %d expected messages. (managed to only read %d messages)",expectedMessageCount,
                        this.size()));
            }
        }
    }
