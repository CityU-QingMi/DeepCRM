    @Override
    public void onDataReceived(ISession session, IStream stream, int length)
    {
        int oldSize = session.updateRecvWindow(-length);
        if (LOG.isDebugEnabled())
            LOG.debug("Data received, {} bytes, updated session recv window {} -> {} for {}", length, oldSize, oldSize - length, session);

        if (stream != null)
        {
            oldSize = stream.updateRecvWindow(-length);
            if (LOG.isDebugEnabled())
                LOG.debug("Data received, {} bytes, updated stream recv window {} -> {} for {}", length, oldSize, oldSize - length, stream);
        }
    }
