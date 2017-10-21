    protected void notifyFrame(final Frame f)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("{} Notify {}",policy.getBehavior(),getIncomingFramesHandler());

        if (policy.getBehavior() == WebSocketBehavior.SERVER)
        {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
            if (!f.isMasked())
            {
                throw new ProtocolException("Client MUST mask all frames (RFC-6455: Section 5.1)");
            }
        }
        else if(policy.getBehavior() == WebSocketBehavior.CLIENT)
        {
            // Required by RFC-6455 / Section 5.1
            if (f.isMasked())
            {
                throw new ProtocolException("Server MUST NOT mask any frames (RFC-6455: Section 5.1)");
            }
        }

        if (incomingFramesHandler == null)
        {
            return;
        }
        try
        {
            incomingFramesHandler.incomingFrame(f);
        }
        catch (WebSocketException e)
        {
            notifyWebSocketException(e);
        }
        catch (Throwable t)
        {
            LOG.warn(t);
            notifyWebSocketException(new WebSocketException(t));
        }
    }
