    @Override
    public void onTextFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (events.onText == null)
        {
            // not interested in text events
            return;
        }

        if (activeMessage == null)
        {
            if (events.onText.isStreaming())
            {
                activeMessage = new MessageReader(new MessageInputStream());
                final MessageAppender msg = activeMessage;
                dispatch(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            events.onText.call(websocket,session,msg);
                        }
                        catch (Throwable t)
                        {
                            // dispatched calls need to be reported
                            onError(t);
                        }
                    }
                });
            }
            else
            {
                activeMessage = new SimpleTextMessage(this);
            }
        }

        appendMessage(buffer,fin);
    }
