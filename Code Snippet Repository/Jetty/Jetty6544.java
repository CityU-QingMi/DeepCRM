    @Override
    public void onBinaryFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (events.onBinary == null)
        {
            // not interested in binary events
            return;
        }

        if (activeMessage == null)
        {
            if (events.onBinary.isStreaming())
            {
                activeMessage = new MessageInputStream();
                final MessageAppender msg = activeMessage;
                dispatch(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            events.onBinary.call(websocket,session,msg);
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
                activeMessage = new SimpleBinaryMessage(this);
            }
        }

        appendMessage(buffer,fin);
    }
