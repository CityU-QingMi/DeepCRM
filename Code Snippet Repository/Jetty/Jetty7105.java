    @Test
    public void testCase5_12() throws Exception
    {
        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new ContinuationFrame().setPayload("sorry").setFin(false));
        send.add(new TextFrame().setPayload("hello, world"));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.PROTOCOL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this);StacklessLogging supress = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.sendAndIgnoreBrokenPipe(send);
            fuzzer.expect(expect);
        }
    }
