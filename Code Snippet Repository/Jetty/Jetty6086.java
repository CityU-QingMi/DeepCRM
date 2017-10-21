    public void callTextStream(RemoteEndpoint.Async endpoint, Object websocket, Reader reader) throws DecodeException, IOException
    {
        if (onTextStream == null)
        {
            return;
        }
        Object ret = onTextStream.call(websocket,reader);
        if (ret != null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("returning: {}",ret);
            }
            endpoint.sendObject(ret);
        }
    }
