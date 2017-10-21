    @Override
    public void sendPartialString(String fragment, boolean isLast) throws IOException
    {
        boolean first = lockMsg(MsgType.PARTIAL_TEXT);
        try
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("sendPartialString({}, {})", fragment, isLast);
            }
            DataFrame frame = first ? new TextFrame() : new ContinuationFrame();
            frame.setPayload(BufferUtil.toBuffer(fragment, StandardCharsets.UTF_8));
            frame.setFin(isLast);
            blockingWrite(frame);
        }
        finally
        {
            if (isLast)
                unlockMsg(MsgType.PARTIAL_TEXT);
        }
    }
