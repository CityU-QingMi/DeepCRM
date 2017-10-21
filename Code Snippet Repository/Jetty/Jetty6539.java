    private void unhandled(Throwable t)
    {
        TARGET_LOG.warn("Unhandled Error (closing connection)",t);
        onError(t);

        if (t instanceof CloseException)
        {
            terminateConnection(((CloseException)t).getStatusCode(),t.getClass().getSimpleName());
            return;
        }

        // Unhandled Error, close the connection.
        switch (policy.getBehavior())
        {
            case SERVER:
                terminateConnection(StatusCode.SERVER_ERROR,t.getClass().getSimpleName());
                break;
            case CLIENT:
                terminateConnection(StatusCode.POLICY_VIOLATION,t.getClass().getSimpleName());
                break;
        }
    }
