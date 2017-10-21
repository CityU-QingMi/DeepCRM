    @Override
    protected void onIdleExpired(TimeoutException timeout)
    {
        Connection connection = _connection;
        if (connection != null && !connection.onIdleExpired())
            return;

        boolean output_shutdown=isOutputShutdown();
        boolean input_shutdown=isInputShutdown();
        boolean fillFailed = _fillInterest.onFail(timeout);
        boolean writeFailed = _writeFlusher.onFail(timeout);

        // If the endpoint is half closed and there was no fill/write handling, then close here.
        // This handles the situation where the connection has completed its close handling
        // and the endpoint is half closed, but the other party does not complete the close.
        // This perhaps should not check for half closed, however the servlet spec case allows
        // for a dispatched servlet or suspended request to extend beyond the connections idle
        // time.  So if this test would always close an idle endpoint that is not handled, then
        // we would need a mode to ignore timeouts for some HTTP states
        if (isOpen() && (output_shutdown || input_shutdown) && !(fillFailed || writeFailed))
            close();
        else
            LOG.debug("Ignored idle endpoint {}",this);
    }
