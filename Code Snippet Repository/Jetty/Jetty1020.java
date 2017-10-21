    @Override
    public void onWindowUpdate(ISession session, IStream stream, WindowUpdateFrame frame)
    {
        int delta = frame.getWindowDelta();
        if (frame.getStreamId() > 0)
        {
            // The stream may have been removed concurrently.
            if (stream != null)
            {
                int oldSize = stream.updateSendWindow(delta);
                if (LOG.isDebugEnabled())
                    LOG.debug("Updated stream send window {} -> {} for {}", oldSize, oldSize + delta, stream);
                if (oldSize <= 0)
                    onStreamUnstalled(stream);
            }
        }
        else
        {
            int oldSize = session.updateSendWindow(delta);
            if (LOG.isDebugEnabled())
                LOG.debug("Updated session send window {} -> {} for {}", oldSize, oldSize + delta, session);
            if (oldSize <= 0)
                onSessionUnstalled(session);
        }
    }
