    public void callText(RemoteEndpoint.Async endpoint, Object websocket, String text, boolean fin) throws DecodeException
    {
        if (onText == null)
        {
            return;
        }
        Object ret = onText.call(websocket,text,fin);
        if (ret != null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("returning: {}",ret);
            }
            endpoint.sendObject(ret);
        }
    }
