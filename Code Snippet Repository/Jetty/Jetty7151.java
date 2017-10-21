    private void assertSlowFrameEcho(byte opcode, int overallMsgSize, int segmentSize) throws Exception
    {
        byte msg[] = new byte[overallMsgSize];
        Arrays.fill(msg,(byte)'M');
        ByteBuffer buf = ByteBuffer.wrap(msg);

        List<WebSocketFrame> send = new ArrayList<>();
        send.add(toDataFrame(opcode).setPayload(buf));
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(toDataFrame(opcode).setPayload(clone(buf)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.SLOW);
            fuzzer.setSlowSendSegmentSize(segmentSize);
            fuzzer.send(send);
            fuzzer.expect(expect,8,TimeUnit.SECONDS);
        }
    }
