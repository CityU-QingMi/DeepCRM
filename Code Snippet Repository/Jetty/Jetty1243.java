    public Runnable onRequestTrailers(HeadersFrame frame)
    {
        HttpFields trailers = frame.getMetaData().getFields();
        if (trailers.size() > 0)
            onTrailers(trailers);

        if (LOG.isDebugEnabled())
        {
            Stream stream = getStream();
            LOG.debug("HTTP2 Request #{}/{}, trailers:{}{}",
                    stream.getId(), Integer.toHexString(stream.getSession().hashCode()),
                    System.lineSeparator(), trailers);
        }

        boolean handle = onRequestComplete();

        boolean wasDelayed = _delayedUntilContent;
        _delayedUntilContent = false;
        return handle || wasDelayed ? this : null;
    }
