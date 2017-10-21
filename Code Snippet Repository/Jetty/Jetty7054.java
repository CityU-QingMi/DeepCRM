    @Test
    public void testCase1_1_2() throws Exception
    {
        byte payload[] = new byte[125];
        Arrays.fill(payload,(byte)'*');
        ByteBuffer buf = ByteBuffer.wrap(payload);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new TextFrame().setPayload(buf));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new TextFrame().setPayload(clone(buf)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
