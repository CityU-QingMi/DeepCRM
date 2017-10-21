    @Override
    public void onBinaryFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (activeMessage == null)
        {
            final MessageHandlerWrapper wrapper = jsrsession.getMessageHandlerWrapper(MessageType.BINARY);
            if (wrapper == null)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("No BINARY MessageHandler declared");
                }
                return;
            }
            if (wrapper.wantsPartialMessages())
            {
                activeMessage = new BinaryPartialMessage(wrapper);
            }
            else if (wrapper.wantsStreams())
            {
                final MessageInputStream stream = new MessageInputStream();
                activeMessage = stream;
                dispatch(new Runnable()
                {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void run()
                    {
                        MessageHandler.Whole<InputStream> handler = (Whole<InputStream>)wrapper.getHandler();
                        handler.onMessage(stream);
                    }
                });
            }
            else
            {
                activeMessage = new BinaryWholeMessage(this,wrapper);
            }
        }

        activeMessage.appendFrame(buffer,fin);

        if (fin)
        {
            activeMessage.messageComplete();
            activeMessage = null;
        }
    }
