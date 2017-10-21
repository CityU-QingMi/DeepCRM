    @Test
    public void testSuspendReadResume100() throws Exception
    {
        String response;
        _handler.setSuspendFor(10000);
        _handler.setRead(-1);
        _handler.setResumeAfter(100);
        _handler.setCompleteAfter(-1);
        response = process("wibble");
        check(response, "DISPATCHED");
    }
