    private void assertMultiFrameEcho(byte opcode, int overallMsgSize, int fragmentSize) throws Exception
    {
        byte msg[] = new byte[overallMsgSize];
        Arrays.fill(msg,(byte)'M');

        List<WebSocketFrame> send = new ArrayList<>();
        byte frag[];
        int remaining = msg.length;
        int offset = 0;
        boolean fin;
        ByteBuffer buf;
        ;
        byte op = opcode;
        while (remaining > 0)
        {
            int len = Math.min(remaining,fragmentSize);
            frag = new byte[len];
            System.arraycopy(msg,offset,frag,0,len);
            remaining -= len;
            fin = (remaining <= 0);
            buf = ByteBuffer.wrap(frag);

            send.add(toDataFrame(op).setPayload(buf).setFin(fin));

            offset += len;
            op = OpCode.CONTINUATION;
        }
        send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        List<WebSocketFrame> expect = new ArrayList<>();
        expect.add(toDataFrame(opcode).setPayload(copyOf(msg)));
        expect.add(new CloseInfo(StatusCode.NORMAL).asFrame());

        try(Fuzzer fuzzer = new Fuzzer(this))
        {
            fuzzer.connect();
            fuzzer.setSendMode(Fuzzer.SendMode.BULK);
            fuzzer.send(send);
            fuzzer.expect(expect,8,TimeUnit.SECONDS);
        }
    }
