    @Test
    public void testCase9_2_1() throws Exception
    {
        byte data[] = new byte[64 * KBYTE];
        Arrays.fill(data,(byte)0x21);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new BinaryFrame().setPayload(data));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new BinaryFrame().setPayload(copyOf(data)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
