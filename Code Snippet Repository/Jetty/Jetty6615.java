    private ReadMode readDiscard(ByteBuffer buffer)
    {
        EndPoint endPoint = getEndPoint();
        try
        {
            while (true)
            {
                int filled = endPoint.fill(buffer);
                if (filled == 0)
                {
                    return ReadMode.DISCARD;
                }
                else if (filled < 0)
                {
                    if (LOG_CLOSE.isDebugEnabled())
                        LOG_CLOSE.debug("read - EOF Reached (remote: {})",getRemoteAddress());
                    return ReadMode.EOF;
                }
                else
                {
                    if (LOG_CLOSE.isDebugEnabled())
                        LOG_CLOSE.debug("Discarded {} bytes - {}",filled,BufferUtil.toDetailString(buffer));
                }
            }
        }
        catch (IOException e)
        {
            LOG.ignore(e);
            return ReadMode.EOF;
        }
        catch (Throwable t)
        {
            LOG.ignore(t);
            return ReadMode.DISCARD;
        }
    }
