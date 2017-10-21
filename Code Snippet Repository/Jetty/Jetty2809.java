    protected void handleException(Throwable failure)
    {
        // Unwrap wrapping Jetty exceptions.
        if (failure instanceof RuntimeIOException)
            failure = failure.getCause();

        if (failure instanceof QuietException || !getServer().isRunning())
        {
            if (LOG.isDebugEnabled())
                LOG.debug(_request.getRequestURI(), failure);
        }
        else if (failure instanceof BadMessageException | failure instanceof IOException | failure instanceof TimeoutException)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(_request.getRequestURI(), failure);
            else
                LOG.warn("{} {}",_request.getRequestURI(), failure);
        }
        else
        {
            LOG.warn(_request.getRequestURI(), failure);
        }

        try
        {
            _state.onError(failure);
        }
        catch (Throwable e)
        {
            failure.addSuppressed(e);
            LOG.warn("ERROR dispatch failed", failure);
            // Try to send a minimal response.
            minimalErrorResponse(failure);
        }
    }
