    protected void onRequestTimeout(HttpServletRequest request, HttpServletResponse response, Thread handlingThread)
    {
        try
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Timing out {}", request);
            response.sendError(HttpStatus.SERVICE_UNAVAILABLE_503);
        }
        catch (Throwable x)
        {
            LOG.info(x);
        }

        handlingThread.interrupt();
    }
