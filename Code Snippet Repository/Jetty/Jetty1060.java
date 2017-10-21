    @Override
    public void onReset(ResetFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Received {}", frame);

        IStream stream = getStream(frame.getStreamId());
        if (stream != null)
            stream.process(frame, Callback.NOOP);
        else
            notifyReset(this, frame);
    }
