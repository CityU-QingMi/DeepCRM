    @Override
    public void sendBinary(ByteBuffer data, SendHandler handler)
    {
        assertMessageNotNull(data);
        assertSendHandlerNotNull(handler);
        if (LOG.isDebugEnabled())
        {
            LOG.debug("sendBinary({},{})",BufferUtil.toDetailString(data),handler);
        }
        WebSocketFrame frame = new BinaryFrame().setPayload(data).setFin(true);
        jettyRemote.uncheckedSendFrame(frame,new SendHandlerWriteCallback(handler));
    }
