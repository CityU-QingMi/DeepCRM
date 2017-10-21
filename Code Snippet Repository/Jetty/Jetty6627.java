    @Override
    public void onFillable()
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} onFillable()",policy.getBehavior());
        stats.countOnFillableEvents.incrementAndGet();

        ByteBuffer buffer = bufferPool.acquire(getInputBufferSize(),true);

        try
        {
            isFilling = true;

            if(readMode == ReadMode.PARSE)
            {
                readMode = readParse(buffer);
            }
            else
            {
                readMode = readDiscard(buffer);
            }
        }
        finally
        {
            bufferPool.release(buffer);
        }

        if ((readMode != ReadMode.EOF) && (suspendToken.get() == false))
        {
            fillInterested();
        }
        else
        {
            isFilling = false;
        }
    }
