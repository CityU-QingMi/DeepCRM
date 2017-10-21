    @Override
    public void onDataSending(IStream stream, int length)
    {
        if (length == 0)
            return;

        ISession session = stream.getSession();
        int oldSessionWindow = session.updateSendWindow(-length);
        int newSessionWindow = oldSessionWindow - length;
        if (LOG.isDebugEnabled())
            LOG.debug("Sending, session send window {} -> {} for {}", oldSessionWindow, newSessionWindow, session);
        if (newSessionWindow <= 0)
            onSessionStalled(session);

        int oldStreamWindow = stream.updateSendWindow(-length);
        int newStreamWindow = oldStreamWindow - length;
        if (LOG.isDebugEnabled())
            LOG.debug("Sending, stream send window {} -> {} for {}", oldStreamWindow, newStreamWindow, stream);
        if (newStreamWindow <= 0)
            onStreamStalled(stream);
    }
