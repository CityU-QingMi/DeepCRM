    public void onPartialBinaryMessage(ByteBuffer buffer, boolean fin)
    {
        try
        {
            events.callBinary(jsrsession.getAsyncRemote(),websocket,buffer,fin);
        }
        catch (DecodeException e)
        {
            throw new RuntimeException("Unable decode partial binary message", e);
        }
    }
