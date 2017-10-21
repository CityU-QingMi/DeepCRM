    protected IStream createRemoteStream(int streamId)
    {
        // SPEC: exceeding max concurrent streams is treated as stream error.
        while (true)
        {
            int remoteCount = remoteStreamCount.get();
            int maxCount = getMaxRemoteStreams();
            if (maxCount >= 0 && remoteCount >= maxCount)
            {
                reset(new ResetFrame(streamId, ErrorCode.REFUSED_STREAM_ERROR.code), Callback.NOOP);
                return null;
            }
            if (remoteStreamCount.compareAndSet(remoteCount, remoteCount + 1))
                break;
        }

        IStream stream = newStream(streamId, false);

        // SPEC: duplicate stream is treated as connection error.
        if (streams.putIfAbsent(streamId, stream) == null)
        {
            updateLastStreamId(streamId);
            stream.setIdleTimeout(getStreamIdleTimeout());
            flowControl.onStreamCreated(stream);
            if (LOG.isDebugEnabled())
                LOG.debug("Created remote {}", stream);
            return stream;
        }
        else
        {
            close(ErrorCode.PROTOCOL_ERROR.code, "duplicate_stream", Callback.NOOP);
            return null;
        }
    }
