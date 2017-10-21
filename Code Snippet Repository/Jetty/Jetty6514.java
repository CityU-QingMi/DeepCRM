    @Override
    public void sendString(String text, WriteCallback callback)
    {
        lockMsg(MsgType.ASYNC);
        try
        {
            TextFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendString({},{})", BufferUtil.toDetailString(frame.getPayload()), callback);
            }
            uncheckedSendFrame(frame, callback == null ? NOOP_CALLBACK : callback);
        }
        finally
        {
            unlockMsg(MsgType.ASYNC);
        }
    }
