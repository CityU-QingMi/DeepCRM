    @Test
    public void testSuspendTimeout() throws Exception
    {
        String response;
        _handler.setRead(0);
        _handler.setSuspendFor(1000);
        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(-1);
        response = process(null);
        check(response, "TIMEOUT");
    }
