    public void onTrailers(IStream stream, HeadersFrame frame)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Processing trailers {} on {}", frame, stream);
        HttpChannelOverHTTP2 channel = (HttpChannelOverHTTP2)stream.getAttribute(IStream.CHANNEL_ATTRIBUTE);
        if (channel != null)
        {
            Runnable task = channel.onRequestTrailers(frame);
            if (task != null)
                offerTask(task, false);
        }
    }
