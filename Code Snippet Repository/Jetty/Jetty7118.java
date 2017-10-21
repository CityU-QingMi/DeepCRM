    @Test
    public void testCase6_2_2() throws Exception
    {
        String utf1 = "Hello-\uC2B5@\uC39F\uC3A4";
        String utf2 = "\uC3BC\uC3A0\uC3A1-UTF-8!!";

        ByteBuffer b1 = ByteBuffer.wrap(StringUtil.getUtf8Bytes(utf1));
        ByteBuffer b2 = ByteBuffer.wrap(StringUtil.getUtf8Bytes(utf2));

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new TextFrame().setPayload(b1).setFin(false));
        send.add(new ContinuationFrame().setPayload(b2).setFin(true));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        ByteBuffer e1 = ByteBuffer.allocate(100);
        e1.put(StringUtil.getUtf8Bytes(utf1));
        e1.put(StringUtil.getUtf8Bytes(utf2));
        e1.flip();
        expect.add(new TextFrame().setPayload(e1));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
