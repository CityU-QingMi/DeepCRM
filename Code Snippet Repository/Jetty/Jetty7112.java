    @Test
    @Slow
    public void testCase6_4_2() throws Exception
    {
        byte part1[] = Hex.asByteArray("CEBAE1BDB9CF83CEBCCEB5F4"); // split code point
        byte part2[] = Hex.asByteArray("90"); // continue code point & invalid
        byte part3[] = Hex.asByteArray("8080656469746564"); // continue code point & finish

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new CloseInfo(StatusCode.BAD_PAYLOAD).asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(new TextFrame().setPayload(ByteBuffer.wrap(part1)).setFin(false));
            TimeUnit.SECONDS.sleep(1);
            fuzzer.send(new ContinuationFrame().setPayload(ByteBuffer.wrap(part2)).setFin(false));
            TimeUnit.SECONDS.sleep(1);
            fuzzer.send(new ContinuationFrame().setPayload(ByteBuffer.wrap(part3)).setFin(true));
            fuzzer.expect(expect);
        }
    }
