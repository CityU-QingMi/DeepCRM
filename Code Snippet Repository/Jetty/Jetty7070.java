    @Test
    public void testCase2_5() throws Exception
    {
        try (StacklessLogging scope = new StacklessLogging(Parser.class))
        {
            byte payload[] = new byte[126]; // intentionally too big
            Arrays.fill(payload,(byte)'5');
            ByteBuffer buf = ByteBuffer.wrap(payload);

            List<WebSocketFrame> send = new ArrayList<>();
            // trick websocket frame into making extra large payload for ping
            send.add(new BadFrame(OpCode.PING).setPayload(buf));
            send.add(new CloseInfo(StatusCode.NORMAL,"Test 2.5").asFrame());

            List<WebSocketFrame> expect = new ArrayList<>();
            expect.add(new CloseInfo(StatusCode.PROTOCOL).asFrame());

            try (Fuzzer fuzzer = new Fuzzer(this))
            {
                fuzzer.connect();
                fuzzer.setSendMode(Fuzzer.SendMode.BULK);
                fuzzer.send(send);
                fuzzer.expect(expect);
            }
        }
    }
