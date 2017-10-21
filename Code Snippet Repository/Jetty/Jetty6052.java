    @Override
    public Future<Void> sendBinary(ByteBuffer data)
    {
        assertMessageNotNull(data);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendBinary({})",BufferUtil.toDetailString(data));
        }
        return jettyRemote.sendBytesByFuture(data);
    }
