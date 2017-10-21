    public void onData(IStream stream, DataFrame frame, Callback callback)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Processing {} on {}", frame, stream);
        HttpChannelOverHTTP2 channel = (HttpChannelOverHTTP2)stream.getAttribute(IStream.CHANNEL_ATTRIBUTE);
        if (channel != null)
        {
            Runnable task = channel.onRequestContent(frame, callback);
            if (task != null)
                offerTask(task, false);
        }
        else
        {
            callback.failed(new IOException("channel_not_found"));
        }
    }
