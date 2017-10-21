    public boolean onSessionTimeout(Throwable failure)
    {
        ISession session = getSession();
        boolean result = true;
        for (Stream stream : session.getStreams())
        {
            HttpChannelOverHTTP2 channel = (HttpChannelOverHTTP2)stream.getAttribute(IStream.CHANNEL_ATTRIBUTE);
            if (channel != null)
                result &= channel.isRequestIdle();
        }
        if (LOG.isDebugEnabled())
            LOG.debug("{} idle timeout on {}: {}", result ? "Processed" : "Ignored", session, failure);
        return result;
    }
