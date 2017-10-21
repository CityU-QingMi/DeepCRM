    @Override
    public void onClose(CloseInfo close)
    {
        if (hasCloseBeenCalled)
        {
            // avoid duplicate close events (possible when using harsh Session.disconnect())
            return;
        }
        hasCloseBeenCalled = true;

        int statusCode = close.getStatusCode();
        String reason = close.getReason();
        listener.onWebSocketClose(statusCode,reason);
    }
