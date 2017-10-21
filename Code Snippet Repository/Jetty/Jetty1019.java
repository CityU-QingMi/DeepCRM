    @Override
    public void updateInitialStreamWindow(ISession session, int initialStreamWindow, boolean local)
    {
        int previousInitialStreamWindow;
        if (local)
        {
            previousInitialStreamWindow = getInitialStreamRecvWindow();
            this.initialStreamRecvWindow = initialStreamWindow;
        }
        else
        {
            previousInitialStreamWindow = getInitialStreamSendWindow();
            this.initialStreamSendWindow = initialStreamWindow;
        }
        int delta = initialStreamWindow - previousInitialStreamWindow;

        // SPEC: updates of the initial window size only affect stream windows, not session's.
        for (Stream stream : session.getStreams())
        {
            if (local)
            {
                ((IStream)stream).updateRecvWindow(delta);
                if (LOG.isDebugEnabled())
                    LOG.debug("Updated initial stream recv window {} -> {} for {}", previousInitialStreamWindow, initialStreamWindow, stream);
            }
            else
            {
                session.onWindowUpdate((IStream)stream, new WindowUpdateFrame(stream.getId(), delta));
            }
        }
    }
