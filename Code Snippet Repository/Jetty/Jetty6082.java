    public void callBinary(RemoteEndpoint.Async endpoint, Object websocket, ByteBuffer buf, boolean fin) throws DecodeException
    {
        if (onBinary == null)
        {
            return;
        }

        Object ret = onBinary.call(websocket,buf,fin);
        if (ret != null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("returning: {}",ret);
            }
            endpoint.sendObject(ret);
        }
    }
