    @Test
    public void testCase3_6() throws Exception
    {
        byte payload[] = new byte[8];
        Arrays.fill(payload,(byte)0xFF);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new PingFrame().setPayload(payload).setRsv3(true).setRsv2(true)); // intentionally bad

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.PROTOCOL).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this); StacklessLogging logging = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
