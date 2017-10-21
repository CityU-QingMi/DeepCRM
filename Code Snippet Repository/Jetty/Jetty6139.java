    @Override
    public void onTextFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (activeMessage == null)
        {
            final MessageHandlerWrapper wrapper = jsrsession.getMessageHandlerWrapper(MessageType.TEXT);
            if (wrapper == null)
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug("No TEXT MessageHandler declared");
                }
                return;
            }
            if (wrapper.wantsPartialMessages())
            {
                activeMessage = new TextPartialMessage(wrapper);
            }
            else if (wrapper.wantsStreams())
            {
                final MessageReader stream = new MessageReader(new MessageInputStream());
                activeMessage = stream;

                dispatch(new Runnable()
                {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void run()
                    {
                        MessageHandler.Whole<Reader> handler = (Whole<Reader>)wrapper.getHandler();
                        handler.onMessage(stream);
                    }
                });
            }
            else
            {
                activeMessage = new TextWholeMessage(this,wrapper);
            }
        }

        activeMessage.appendFrame(buffer,fin);

        if (fin)
        {
            activeMessage.messageComplete();
            activeMessage = null;
        }
    }
