    private void process(ByteBuffer buffer)
    {
        try
        {
            EndPoint endPoint = getEndPoint();
            boolean looping = false;
            while (true)
            {
                if (!looping && parse(buffer))
                    return;

                int read = endPoint.fill(buffer);
                if (LOG.isDebugEnabled())
                    LOG.debug("Read {} bytes from {}", read, endPoint);

                if (read > 0)
                {
                    if (parse(buffer))
                        return;
                }
                else if (read == 0)
                {
                    releaseBuffer(buffer);
                    fillInterested();
                    return;
                }
                else
                {
                    releaseBuffer(buffer);
                    shutdown();
                    return;
                }

                looping = true;
            }
        }
        catch (Exception x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(x);
            releaseBuffer(buffer);
            close(x);
        }
    }
