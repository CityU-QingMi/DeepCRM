    @OnWebSocketMessage
    public void onText(String message)
    {
        if (LOG.isDebugEnabled())
        {
            if (message == null)
            {
                LOG.debug("onText() msg=null");
            }
            else
            {
                LOG.debug("onText() size={}, msg={}",message.length(),TextUtil.hint(message));
            }
        }

        try
        {
            // echo the message back.
            this.session.getRemote().sendString(message,null);
        }
        catch (WebSocketException e)
        {
            LOG.warn("Unable to echo TEXT message",e);
        }
    }
