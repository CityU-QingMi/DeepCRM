    @Override
    public void onFillable()
    {
        EndPoint endPoint = getEndPoint();
        ByteBufferPool bufferPool = connector.getByteBufferPool();
        ByteBuffer buffer = bufferPool.acquire(configuration.getResponseHeaderSize(), true);
        try
        {
            while (true)
            {
                int read = endPoint.fill(buffer);
                if (LOG.isDebugEnabled()) // Avoid boxing of variable 'read'
                    LOG.debug("Read {} bytes from {}", read, endPoint);
                if (read > 0)
                {
                    parse(buffer);
                }
                else if (read == 0)
                {
                    bufferPool.release(buffer);
                    fillInterested();
                    break;
                }
                else
                {
                    bufferPool.release(buffer);
                    shutdown();
                    break;
                }
            }
        }
        catch (Exception x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(x);
            bufferPool.release(buffer);
            // TODO: fail and close ?
        }
    }
