    @Test
    public void assertBadTextPayload() throws Exception
    {
        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new TextFrame().setPayload(ByteBuffer.wrap(invalid)));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.BAD_PAYLOAD).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            try (StacklessLogging supress = new StacklessLogging(Parser.class))
            {
                fuzzer.connect();
                fuzzer.setSendMode(Fuzzer.SendMode.BULK);
                fuzzer.send(send);
                fuzzer.expect(expect);
            }
        }
    }
