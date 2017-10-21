    public boolean abort(Throwable failure)
    {
        // Atomically change the state of this exchange to be completed.
        // This will avoid that this exchange can be associated to a channel.
        boolean abortRequest;
        boolean abortResponse;
        synchronized (this)
        {
            abortRequest = completeRequest(failure);
            abortResponse = completeResponse(failure);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Failed {}: req={}/rsp={} {}", this, abortRequest, abortResponse, failure);

        if (!abortRequest && !abortResponse)
            return false;

        // We failed this exchange, deal with it.

        // Case #1: exchange was in the destination queue.
        if (destination.remove(this))
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Aborting while queued {}: {}", this, failure);
            notifyFailureComplete(failure);
            return true;
        }

        HttpChannel channel = getHttpChannel();
        if (channel == null)
        {
            // Case #2: exchange was not yet associated.
            // Because this exchange is failed, when associate() is called
            // it will return false, and the caller will dispose the channel.
            if (LOG.isDebugEnabled())
                LOG.debug("Aborted before association {}: {}", this, failure);
            notifyFailureComplete(failure);
            return true;
        }

        // Case #3: exchange was already associated.
        boolean aborted = channel.abort(this, abortRequest ? failure : null, abortResponse ? failure : null);
        if (LOG.isDebugEnabled())
            LOG.debug("Aborted ({}) while active {}: {}", aborted, this, failure);
        return aborted;
    }
