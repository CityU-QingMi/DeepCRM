    @Override
    public void onWindowUpdate(WindowUpdateFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Received {}", frame);

        int streamId = frame.getStreamId();
        if (streamId > 0)
        {
            IStream stream = getStream(streamId);
            if (stream != null)
            {
                stream.process(frame, Callback.NOOP);
                onWindowUpdate(stream, frame);
            }
        }
        else
        {
            onWindowUpdate(null, frame);
        }
    }
