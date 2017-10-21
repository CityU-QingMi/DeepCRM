    @Test
    public void testCase7_3_1() throws Exception
    {
        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new CloseFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
            fuzzer.expectNoMoreFrames();
        }
    }
