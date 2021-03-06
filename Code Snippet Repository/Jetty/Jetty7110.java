    @Test
    public void testCase5_17() throws Exception
    {
        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new ContinuationFrame().setPayload("fragment1").setFin(true)); // nothing to continue
        send.add(new TextFrame().setPayload("fragment2").setFin(false));
        send.add(new ContinuationFrame().setPayload("fragment3").setFin(true));
        send.add(new ContinuationFrame().setPayload("fragment4").setFin(true)); // nothing to continue
        send.add(new TextFrame().setPayload("fragment5").setFin(false));
        send.add(new ContinuationFrame().setPayload("fragment6").setFin(true));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.PROTOCOL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this);StacklessLogging supress = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
