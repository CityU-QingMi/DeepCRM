    @Test
    public void testCase9_1_4() throws Exception
    {
        byte utf[] = new byte[4 * MBYTE];
        Arrays.fill(utf,(byte)'y');
        ByteBuffer buf = ByteBuffer.wrap(utf);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new TextFrame().setPayload(buf));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new TextFrame().setPayload(clone(buf)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect,8,TimeUnit.SECONDS);
        }
    }
