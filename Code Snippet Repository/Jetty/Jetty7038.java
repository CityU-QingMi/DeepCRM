    @Test
    public void testTextNotUTF8() throws Exception
    {
        try (StacklessLogging stackless=new StacklessLogging(Parser.class);
             BlockheadClient client = new BlockheadClient(server.getServerUri()))
        {
            client.setProtocols("other");
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            byte buf[] = new byte[]
            { (byte)0xC2, (byte)0xC3 };

            WebSocketFrame txt = new TextFrame().setPayload(ByteBuffer.wrap(buf));
            txt.setMask(Hex.asByteArray("11223344"));
            ByteBuffer bbHeader = generator.generateHeaderBytes(txt);
            client.writeRaw(bbHeader);
            client.writeRaw(txt.getPayload());

            EventQueue<WebSocketFrame> frames = client.readFrames(1,30,TimeUnit.SECONDS);
            WebSocketFrame frame = frames.poll();
            Assert.assertThat("frames[0].opcode",frame.getOpCode(),is(OpCode.CLOSE));
            CloseInfo close = new CloseInfo(frame);
            Assert.assertThat("Close Status Code",close.getStatusCode(),is(StatusCode.BAD_PAYLOAD));
        }
    }
