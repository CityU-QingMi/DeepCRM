    @Override
    public void onHeaders(HeadersFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Received {}", frame);

        MetaData metaData = frame.getMetaData();
        if (metaData.isRequest())
        {
            IStream stream = createRemoteStream(frame.getStreamId());
            if (stream != null)
            {
                onStreamOpened(stream);
                stream.process(frame, Callback.NOOP);
                Stream.Listener listener = notifyNewStream(stream, frame);
                stream.setListener(listener);
            }
        }
        else if (metaData.isResponse())
        {
            onConnectionFailure(ErrorCode.PROTOCOL_ERROR.code, "invalid_request");
        }
        else
        {
            // Trailers.
            int streamId = frame.getStreamId();
            IStream stream = getStream(streamId);
            if (stream != null)
            {
                stream.process(frame, Callback.NOOP);
                notifyHeaders(stream, frame);
            }
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Ignoring {}, stream #{} not found", frame, streamId);
            }
        }
    }
