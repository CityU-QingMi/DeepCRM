    @Test
    public void testCase7_5_1() throws Exception
    {
        ByteBuffer payload = ByteBuffer.allocate(256);
        BufferUtil.clearToFill(payload);
        payload.put((byte)0x03); // normal close
        payload.put((byte)0xE8);
        byte invalidUtf[] = Hex.asByteArray("CEBAE1BDB9CF83CEBCCEB5EDA080656469746564");
        payload.put(invalidUtf);
        BufferUtil.flipToFlush(payload,0);

        List<WebSocketFrame> send = new ArrayList<>();
        WebSocketFrame close = new BadFrame(OpCode.CLOSE);
        close.setPayload(payload); // intentionally bad payload
        send.add(close);

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.BAD_PAYLOAD).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this); StacklessLogging scope = new StacklessLogging(Parser.class,CloseInfo.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
            fuzzer.expectNoMoreFrames();
        }
    }
