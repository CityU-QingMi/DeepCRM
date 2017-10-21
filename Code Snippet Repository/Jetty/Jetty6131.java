    @Override
    public void onInputStream(InputStream stream) throws IOException
    {
        try
        {
            events.callBinaryStream(jsrsession.getAsyncRemote(),websocket,stream);
        }
        catch (DecodeException e)
        {
            throw new RuntimeException("Unable decode input stream", e);
        }
    }
