    @Test
    public void testSuspendResume0() throws Exception
    {
        String response;
        _handler.setRead(0);
        _handler.setSuspendFor(10000);
        _handler.setResumeAfter(0);
        _handler.setCompleteAfter(-1);
        response = process(null);
        check(response, "STARTASYNC", "DISPATCHED");
    }
