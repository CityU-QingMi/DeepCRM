    @Test
    public void testSuspendComplete0() throws Exception
    {
        String response;
        _handler.setRead(0);
        _handler.setSuspendFor(10000);
        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(0);
        response = process(null);
        check(response, "STARTASYNC", "COMPLETED");
    }
