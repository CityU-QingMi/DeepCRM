    @Override
    public void sendBytes(ByteBuffer data) throws IOException
    {
        lockMsg(MsgType.BLOCKING);
        try
        {
            connection.getIOState().assertOutputOpen();
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendBytes with {}", BufferUtil.toDetailString(data));
            }
            blockingWrite(new BinaryFrame().setPayload(data));
        }
        finally
        {
            unlockMsg(MsgType.BLOCKING);
        }
    }
