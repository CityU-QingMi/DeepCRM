    public void callBinaryStream(RemoteEndpoint.Async endpoint, Object websocket, InputStream stream) throws DecodeException, IOException
    {
        if (onBinaryStream == null)
        {
            return;
        }

        Object ret = onBinaryStream.call(websocket,stream);
        if (ret != null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("returning: {}",ret);
            }
            endpoint.sendObject(ret);
        }
    }
