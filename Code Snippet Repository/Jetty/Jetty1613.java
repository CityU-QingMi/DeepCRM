    protected void onFillInterestedFailed(Throwable cause)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} onFillInterestedFailed {}", this, cause);
        if (_endPoint.isOpen())
        {
            boolean close = true;
            if (cause instanceof TimeoutException)
                close = onReadTimeout();
            if (close)
            {
                if (_endPoint.isOutputShutdown())
                    _endPoint.close();
                else
                {
                    _endPoint.shutdownOutput();
                    fillInterested();
                }
            }
        }
    }
