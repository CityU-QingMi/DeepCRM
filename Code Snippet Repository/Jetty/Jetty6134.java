    @Override
    public void onReader(Reader reader) throws IOException
    {
        try
        {
            events.callTextStream(jsrsession.getAsyncRemote(),websocket,reader);
        }
        catch (DecodeException e)
        {
            throw new RuntimeException("Unable decode reader", e);
        }
    }
