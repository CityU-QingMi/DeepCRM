    @Override
    public void sendText(String text, SendHandler handler)
    {
        assertMessageNotNull(text);
        assertSendHandlerNotNull(handler);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendText({},{})",TextUtil.hint(text),handler);
        }
        WebSocketFrame frame = new TextFrame().setPayload(text).setFin(true);
        jettyRemote.uncheckedSendFrame(frame,new SendHandlerWriteCallback(handler));
    }
