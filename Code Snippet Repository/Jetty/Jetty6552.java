    @Override
    public void onTextFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (listener instanceof WebSocketListener)
        {
            if (activeMessage == null)
            {
                activeMessage = new SimpleTextMessage(this);
            }

            appendMessage(buffer,fin);
        }

        if (listener instanceof WebSocketPartialListener)
        {
            if (utf8Partial == null)
            {
                utf8Partial = new Utf8PartialBuilder();
            }
            
            String partial = utf8Partial.toPartialString(buffer);
            
            ((WebSocketPartialListener)listener).onWebSocketPartialText(partial,fin);
            
            if (fin)
            {
                partial = null;
            }
        }
    }
