    protected void sendError(int code, String reason)
    {
        try
        {
            _response.sendError(code, reason);
        }
        catch (Throwable x)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("Could not send error " + code + " " + reason, x);
        }
        finally
        {
            _state.errorComplete();
        }
    }
