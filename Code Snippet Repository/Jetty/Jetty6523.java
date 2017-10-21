    @Override
    public void sendString(String text) throws IOException
    {
        lockMsg(MsgType.BLOCKING);
        try
        {
            WebSocketFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendString with {}", BufferUtil.toDetailString(frame.getPayload()));
            }
            blockingWrite(frame);
        }
        finally
        {
            unlockMsg(MsgType.BLOCKING);
        }
    }
