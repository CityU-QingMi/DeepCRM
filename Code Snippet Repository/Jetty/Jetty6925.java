    public IncomingFramesCapture readFrames(int expectedCount, int timeoutDuration, TimeUnit timeoutUnit) throws IOException, TimeoutException
    {
        LOG.debug("Read: waiting for {} frame(s) from client",expectedCount);
        int startCount = incomingFrames.size();

        ByteBuffer buf = bufferPool.acquire(BUFFER_SIZE,false);
        BufferUtil.clearToFill(buf);
        try
        {
            long msDur = TimeUnit.MILLISECONDS.convert(timeoutDuration,timeoutUnit);
            long now = System.currentTimeMillis();
            long expireOn = now + msDur;
            LOG.debug("Now: {} - expireOn: {} ({} ms)",now,expireOn,msDur);

            int len = 0;
            while (incomingFrames.size() < (startCount + expectedCount))
            {
                BufferUtil.clearToFill(buf);
                len = read(buf);
                if (len > 0)
                {
                    LOG.debug("Read {} bytes",len);
                    BufferUtil.flipToFlush(buf,0);
                    parser.parse(buf);
                }
                try
                {
                    TimeUnit.MILLISECONDS.sleep(20);
                }
                catch (InterruptedException gnore)
                {
                    /* ignore */
                }
                if (!debug && (System.currentTimeMillis() > expireOn))
                {
                    incomingFrames.dump();
                    throw new TimeoutException(String.format("Timeout reading all %d expected frames. (managed to only read %d frame(s))",expectedCount,
                            incomingFrames.size()));
                }
            }
        }
        finally
        {
            bufferPool.release(buf);
        }

        return incomingFrames;
    }
