    private boolean updateResponseState(ResponseState from, ResponseState to)
    {
        boolean updated = responseState.compareAndSet(from, to);
        if (!updated)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("State update failed: {} -> {}: {}", from, to, responseState.get());
        }
        return updated;
    }
