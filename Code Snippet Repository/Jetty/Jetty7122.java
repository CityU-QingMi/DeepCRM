    @Test
    @Slow
    public void testCase6_4_1() throws Exception
    {
        byte part1[] = StringUtil.getUtf8Bytes("\u03BA\u1F79\u03C3\u03BC\u03B5");
        byte part2[] = Hex.asByteArray("F4908080"); // invalid
        byte part3[] = StringUtil.getUtf8Bytes("edited");

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
