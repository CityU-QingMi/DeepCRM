    public void call(Object endpoint, int statusCode, String reason)
    {
        // Close Reason is an optional parameter
        if (idxCloseReason >= 0)
        {
            // convert to javax.websocket.CloseReason
            CloseReason jsrclose = new CloseReason(CloseCodes.getCloseCode(statusCode),reason);
            super.args[idxCloseReason] = jsrclose;
        }
        super.call(endpoint,super.args);
    }
