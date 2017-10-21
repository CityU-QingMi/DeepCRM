    @Test
    @Slow
    public void testCase6_4_4() throws Exception
    {
        byte invalid[] = Hex.asByteArray("CEBAE1BDB9CF83CEBCCEB5F49080808080656469746564");

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new TextFrame().setPayload(ByteBuffer.wrap(invalid)));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.BAD_PAYLOAD).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this); StacklessLogging scope = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();

            ByteBuffer net = fuzzer.asNetworkBuffer(send);
            fuzzer.send(net,6);
            fuzzer.send(net,11);
            TimeUnit.SECONDS.sleep(1);
            fuzzer.send(net,1);
            TimeUnit.SECONDS.sleep(1);
            fuzzer.send(net,100); // the rest

            fuzzer.expect(expect);
        }
    }
