    @Test
    public void testParserNonControlOpCode3Case4_1_1() throws Exception
    {
        ByteBuffer expected = ByteBuffer.allocate(32);

        expected.put(new byte[] { (byte)0x83, 0x00 });

        expected.flip();

        IncomingFramesCapture capture = new IncomingFramesCapture();

        try (StacklessLogging logging = new StacklessLogging(Parser.class))
        {
            Parser parser = new UnitParser(policy);
            parser.setIncomingFramesHandler(capture);
            try
            {
                parser.parse(expected);
            }
            catch (ProtocolException ignore)
            {
                // ignore
            }
        }

        Assert.assertEquals("error on undefined opcode",1,capture.getErrorCount(WebSocketException.class));

        Throwable known = capture.getErrors().poll();

        Assert.assertTrue("undefined option should be in message",known.getMessage().contains("Unknown opcode: 3"));
    }
