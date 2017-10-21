    public void onStreamFailure(IStream stream, Throwable failure, Callback callback)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("Processing failure on {}: {}", stream, failure);
        HttpChannelOverHTTP2 channel = (HttpChannelOverHTTP2)stream.getAttribute(IStream.CHANNEL_ATTRIBUTE);
        if (channel != null)
        {
            Runnable task = channel.onFailure(failure, callback);
            if (task != null)
                offerTask(task, true);
        }
        else
        {
            callback.succeeded();
        }
    }
