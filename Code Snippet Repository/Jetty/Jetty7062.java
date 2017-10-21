    @Test
    public void testCase2_1() throws Exception
    {
        WebSocketFrame send = new PingFrame();

        WebSocketFrame expect = new PongFrame();

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
