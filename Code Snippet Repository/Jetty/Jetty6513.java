    @Override
    public Future<Void> sendStringByFuture(String text)
    {
        lockMsg(MsgType.ASYNC);
        try
        {
            TextFrame frame = new TextFrame().setPayload(text);
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendStringByFuture with {}", BufferUtil.toDetailString(frame.getPayload()));
            }
            return sendAsyncFrame(frame);
        }
        finally
        {
            unlockMsg(MsgType.ASYNC);
        }
    }
