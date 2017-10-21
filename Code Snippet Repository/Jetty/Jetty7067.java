    @Test
    public void testCase2_2() throws Exception
    {
        byte payload[] = StringUtil.getUtf8Bytes("Hello world");

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new PingFrame().setPayload(payload));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new PongFrame().setPayload(copyOf(payload)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
