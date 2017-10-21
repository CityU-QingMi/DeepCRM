    @Test
    public void testSuspendOther() throws Exception
    {
        String response;
        _handler.setSuspendFor(10000);
        _handler.setRead(-1);
        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(0);
        response = process("wibble");
        check(response, "COMPLETED");

        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(100);
        response = process("wibble");
        check(response, "COMPLETED");
        
        _handler.setRead(6);
        _handler.setResumeAfter(0);
        _handler.setCompleteAfter(-1);
        response = process("wibble");
        check(response, "DISPATCHED");

        _handler.setResumeAfter(100);
        _handler.setCompleteAfter(-1);
        response = process("wibble");
        
        check(response, "DISPATCHED");

        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(0);
        response = process("wibble");
        check(response, "COMPLETED");

        _handler.setResumeAfter(-1);
        _handler.setCompleteAfter(100);
        response = process("wibble");
        check(response, "COMPLETED");
        
    }
