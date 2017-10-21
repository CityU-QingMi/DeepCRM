    @OnMessage
    public void onBinary(InputStream stream)
    {
        try
        {
            String msg = IO.toString(stream);
            addEvent("onBinary(%s)",msg);
        }
        catch (IOException e)
        {
            super.errorQueue.add(e);
        }
        dataLatch.countDown();
    }
