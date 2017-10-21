    public void onSessionFailure(Throwable failure, Callback callback)
    {
        ISession session = getSession();
        if (LOG.isDebugEnabled())
            LOG.debug("Processing failure on {}: {}", session, failure);
        Collection<Stream> streams = session.getStreams();
        if (streams.isEmpty())
        {
            callback.succeeded();
        }
        else
        {
            CountingCallback counter = new CountingCallback(callback, streams.size());
            for (Stream stream : streams)
                onStreamFailure((IStream)stream, failure, counter);
        }
    }
