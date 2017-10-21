    @Override
    public void onClose(CloseInfo close)
    {
        if (hasCloseBeenCalled)
        {
            // avoid duplicate close events (possible when using harsh Session.disconnect())
            return;
        }
        hasCloseBeenCalled = true;
        if (events.onClose != null)
        {
            events.onClose.call(websocket,session,close.getStatusCode(),close.getReason());
        }
    }
