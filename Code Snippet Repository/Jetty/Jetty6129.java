    @Override
    public void onBinaryMessage(byte[] data)
    {
        if (data == null)
        {
            return;
        }

        ByteBuffer buf = ByteBuffer.wrap(data);

        if (LOG.isDebugEnabled())
        {
            LOG.debug("onBinaryMessage({})",BufferUtil.toDetailString(buf));
        }

        try
        {
            // FIN is always true here
            events.callBinary(jsrsession.getAsyncRemote(),websocket,buf,true);
        }
        catch (Throwable e)
        {
            onFatalError(e);
        }
    }
