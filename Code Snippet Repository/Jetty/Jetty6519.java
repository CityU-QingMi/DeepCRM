    @Override
    public void sendBytes(ByteBuffer data, WriteCallback callback)
    {
        lockMsg(MsgType.ASYNC);
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendBytes({}, {})", BufferUtil.toDetailString(data), callback);
            }
            uncheckedSendFrame(new BinaryFrame().setPayload(data), callback == null ? NOOP_CALLBACK : callback);
        }
        finally
        {
            unlockMsg(MsgType.ASYNC);
        }
    }
