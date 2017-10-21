    @Override
    public void sendBinary(ByteBuffer partialByte, boolean isLast) throws IOException
    {
        assertMessageNotNull(partialByte);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendBinary({},{})",BufferUtil.toDetailString(partialByte),isLast);
        }
        jettyRemote.sendPartialBytes(partialByte,isLast);
    }
