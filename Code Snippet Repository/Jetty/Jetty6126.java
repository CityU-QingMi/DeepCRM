    @Override
    public final void onClose(CloseInfo close)
    {
        if (hasCloseBeenCalled)
        {
            // avoid duplicate close events (possible when using harsh Session.disconnect())
            return;
        }
        hasCloseBeenCalled = true;

        CloseCode closecode = CloseCodes.getCloseCode(close.getStatusCode());
        CloseReason closereason = new CloseReason(closecode,close.getReason());
        onClose(closereason);
    }
