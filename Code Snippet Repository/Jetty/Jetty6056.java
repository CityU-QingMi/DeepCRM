    @Override
    public void sendBinary(ByteBuffer data) throws IOException
    {
        assertMessageNotNull(data);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendBinary({})",BufferUtil.toDetailString(data));
        }
        jettyRemote.sendBytes(data);
    }
