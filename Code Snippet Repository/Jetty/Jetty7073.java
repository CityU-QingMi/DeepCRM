    @Test
    public void testCase3_1() throws Exception
    {
        WebSocketFrame send = new TextFrame().setPayload("small").setRsv1(true); // intentionally bad

        WebSocketFrame expect = new CloseInfo(StatusCode.PROTOCOL).asFrame();

        try (Fuzzer fuzzer = new Fuzzer(this); StacklessLogging logging = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
