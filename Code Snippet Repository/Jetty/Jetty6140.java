    private void onPongMessage(ByteBuffer buffer)
    {
        final MessageHandlerWrapper wrapper = jsrsession.getMessageHandlerWrapper(MessageType.PONG);
        if (wrapper == null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("No PONG MessageHandler declared");
            }
            return;
        }

        ByteBuffer pongBuf = null;

        if (BufferUtil.isEmpty(buffer))
        {
            pongBuf = BufferUtil.EMPTY_BUFFER;
        }
        else
        {
            pongBuf = ByteBuffer.allocate(buffer.remaining());
            BufferUtil.put(buffer,pongBuf);
            BufferUtil.flipToFlush(pongBuf,0);
        }

        @SuppressWarnings("unchecked")
        Whole<PongMessage> pongHandler = (Whole<PongMessage>)wrapper.getHandler();
        pongHandler.onMessage(new JsrPongMessage(pongBuf));
    }
