    @Override
    public Future<Void> sendBytesByFuture(ByteBuffer data)
    {
        lockMsg(MsgType.ASYNC);
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendBytesByFuture with {}", BufferUtil.toDetailString(data));
            }
            return sendAsyncFrame(new BinaryFrame().setPayload(data));
        }
        finally
        {
            unlockMsg(MsgType.ASYNC);
        }
    }
