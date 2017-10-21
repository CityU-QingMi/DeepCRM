    @Override
    public void onHeaders(HeadersFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Received {}", frame);

        int streamId = frame.getStreamId();
        IStream stream = getStream(streamId);
        if (stream != null)
        {
            MetaData metaData = frame.getMetaData();
            if (metaData.isRequest())
            {
                onConnectionFailure(ErrorCode.PROTOCOL_ERROR.code, "invalid_response");
            }
            else
            {
                stream.process(frame, Callback.NOOP);
                notifyHeaders(stream, frame);
            }
        }
        else
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Ignoring {}, stream #{} not found", frame, streamId);
        }
    }
