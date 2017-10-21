    private void disconnect(boolean onlyOutput)
    {
        if (LOG_CLOSE.isDebugEnabled())
            LOG_CLOSE.debug("{} disconnect({})",policy.getBehavior(),onlyOutput?"outputOnly":"both");
        // close FrameFlusher, we cannot write anymore at this point.
        flusher.close();
        EndPoint endPoint = getEndPoint();
        // We need to gently close first, to allow
        // SSL close alerts to be sent by Jetty
        if (LOG_CLOSE.isDebugEnabled())
            LOG_CLOSE.debug("Shutting down output {}",endPoint);
        endPoint.shutdownOutput();
        if (!onlyOutput)
        {
            if (LOG_CLOSE.isDebugEnabled())
                LOG_CLOSE.debug("Closing {}",endPoint);
            endPoint.close();
        }
    }
