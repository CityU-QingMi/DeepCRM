    @Test
    public void testCase2_6() throws Exception
    {
        byte payload[] = new byte[125];
        Arrays.fill(payload,(byte)'6');

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(new PingFrame().setPayload(payload));
        send.add(new CloseInfo(StatusCode.NORMAL,"Test 2.6").asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(new PongFrame().setPayload(copyOf(payload)));
        expect.add(new CloseInfo(StatusCode.NORMAL,"Test 2.6").asFrame());

        try (Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.SLOW);
            fuzzer.setSlowSendSegmentSize(1);
            fuzzer.send(send);
            fuzzer.expect(expect);
        }
    }
