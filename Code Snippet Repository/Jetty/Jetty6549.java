    @Override
    public void onBinaryFrame(ByteBuffer buffer, boolean fin) throws IOException
    {
        if (listener instanceof WebSocketListener)
        {
            if (activeMessage == null)
            {
                activeMessage = new SimpleBinaryMessage(this);
            }

            appendMessage(buffer,fin);
        }

        if (listener instanceof WebSocketPartialListener)
        {
            ((WebSocketPartialListener)listener).onWebSocketPartialBinary(buffer.slice().asReadOnlyBuffer(),fin);
        }
    }
