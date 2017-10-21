    @Test
    public void testSuspendReadResume0() throws Exception
    {
        String response;
        _handler.setSuspendFor(10000);
        _handler.setRead(-1);
        _handler.setResumeAfter(0);
        _handler.setCompleteAfter(-1);
        response = process("wibble");
        check(response, "STARTASYNC", "DISPATCHED");
    }
