    public HttpInputIntegrationTest(int id,Class<? extends TestClient> client, Mode mode,boolean dispatch,Boolean delay,int status,int read,int length,String... send)
    {
        _id=id;
        _client=client;
        _mode=mode;
        __config.setDelayDispatchUntilContent(dispatch);
        _delay=delay;
        _status=status;
        _read=read;
        _length=length;
        _send = Arrays.asList(send);
    }
