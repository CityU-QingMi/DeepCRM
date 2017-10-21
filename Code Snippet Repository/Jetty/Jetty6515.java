    @Override
    public void flush() throws IOException
    {
        lockMsg(MsgType.ASYNC);
        try (WriteBlocker b = blocker.acquireWriteBlocker())
        {
            uncheckedSendFrame(FrameFlusher.FLUSH_FRAME, b);
            b.block();
        }
        finally
        {
            unlockMsg(MsgType.ASYNC);
        }
    }
