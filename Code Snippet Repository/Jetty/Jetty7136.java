    @Test
    public void testCase7_3_3() throws Exception
    {
        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
            fuzzer.expectNoMoreFrames();
        }
    }
