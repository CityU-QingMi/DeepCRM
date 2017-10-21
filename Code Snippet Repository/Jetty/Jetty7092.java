    @Test
    @Slow
    public void testCase5_19() throws Exception
    {
        // phase 1
        List<WebSocketFrame> send1 = new ArrayList<>();
        send1.add(new TextFrame().setPayload("f1").setFin(false));
        send1.add(new ContinuationFrame().setPayload(",f2").setFin(false));
        send1.add(new PingFrame().setPayload("pong-1"));

        List<WebSocketFrame> expect1 = new ArrayList<>();
        expect1.add(new PongFrame().setPayload("pong-1"));

        // phase 2
        List<WebSocketFrame> send2 = new ArrayList<>();
        send2.add(new ContinuationFrame().setPayload(",f3").setFin(false));
        send2.add(new ContinuationFrame().setPayload(",f4").setFin(false));
        send2.add(new PingFrame().setPayload("pong-2"));
        send2.add(new ContinuationFrame().setPayload(",f5").setFin(true));
        send2.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect2 = new ArrayList<>();
        expect2.add(new PongFrame().setPayload("pong-2"));
        expect2.add(new TextFrame().setPayload("f1,f2,f3,f4,f5"));
        expect2.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this);StacklessLogging supress = new StacklessLogging(Parser.class))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);

            // phase 1
            fuzzer.send(send1);
            fuzzer.expect(expect1);

            // delay
            TimeUnit.SECONDS.sleep(1);

            // phase 2
            fuzzer.send(send2);
            fuzzer.expect(expect2);
        }
    }
