    @Override
    public void removeStream(IStream stream)
    {
        IStream removed = streams.remove(stream.getId());
        if (removed != null)
        {
            assert removed == stream;

            boolean local = stream.isLocal();
            if (local)
                localStreamCount.decrementAndGet();
            else
                remoteStreamCount.decrementAndGet();

            onStreamClosed(stream);

            flowControl.onStreamDestroyed(stream);

            if (LOG.isDebugEnabled())
                LOG.debug("Removed {} {}", local ? "local" : "remote", stream);
        }
    }
