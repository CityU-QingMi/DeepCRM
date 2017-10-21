    @Override
    public void sendPartialBytes(ByteBuffer fragment, boolean isLast) throws IOException
    {
        boolean first = lockMsg(MsgType.PARTIAL_BINARY);
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendPartialBytes({}, {})", BufferUtil.toDetailString(fragment), isLast);
            }
            DataFrame frame = first ? new BinaryFrame() : new ContinuationFrame();
            frame.setPayload(fragment);
            frame.setFin(isLast);
            blockingWrite(frame);
        }
        finally
        {
            if (isLast)
                unlockMsg(MsgType.PARTIAL_BINARY);
        }
    }
