    public void call(Object endpoint, CloseReason closeReason)
    {
        // Close Reason is an optional parameter
        if (idxCloseReason >= 0)
        {
            // convert to javax.websocket.CloseReason
            super.args[idxCloseReason] = closeReason;
        }
        super.call(endpoint,super.args);
    }
