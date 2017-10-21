    @Override
    public void run()
    {
        LOG.debug("Entering echo thread");

        ByteBuffer buf = bufferPool.acquire(BUFFER_SIZE,false);
        BufferUtil.clearToFill(buf);
        long readBytes = 0;
        try
        {
            while (echoing.get())
            {
                BufferUtil.clearToFill(buf);
                long len = read(buf);
                if (len > 0)
                {
                    readBytes += len;
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
            }
        }
        catch (IOException e)
        {
            LOG.debug("Exception during echo loop",e);
        }
        finally
        {
            LOG.debug("Read {} bytes",readBytes);
            bufferPool.release(buf);
        }
    }
