    @Test
    public void testTwoCycles() throws Exception
    {
        String response;

        _handler.setRead(0);
        _handler.setSuspendFor(1000);
        _handler.setResumeAfter(100);
        _handler.setCompleteAfter(-1);
        _handler.setSuspendFor2(1000);
        _handler.setResumeAfter2(200);
        _handler.setCompleteAfter2(-1);
        response = process(null);
        check(response, "STARTASYNC", "DISPATCHED", "startasync", "STARTASYNC2", "DISPATCHED");
    }
