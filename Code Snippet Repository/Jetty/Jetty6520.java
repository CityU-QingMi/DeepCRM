    public void uncheckedSendFrame(WebSocketFrame frame, WriteCallback callback)
    {
        try
        {
            BatchMode batchMode = BatchMode.OFF;
            if (frame.isDataFrame())
                batchMode = getBatchMode();
            connection.getIOState().assertOutputOpen();
            outgoing.outgoingFrame(frame, callback, batchMode);
        }
        catch (IOException e)
        {
            callback.writeFailed(e);
        }
    }
