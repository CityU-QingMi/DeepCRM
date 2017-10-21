    @Test
    public void testStatusCodeWithReason() throws Exception
    {
        ByteBuffer payload = ByteBuffer.allocate(256);
        payload.putChar((char)statusCode);
        payload.put(StringUtil.getBytes("Reason"));
        payload.flip();

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new CloseFrame().setPayload(payload.slice()));

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseFrame().setPayload(clone(payload)));

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
            fuzzer.expectNoMoreFrames();
        }
    }
