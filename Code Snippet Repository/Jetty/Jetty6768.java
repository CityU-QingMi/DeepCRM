    @Test
    public void testParseOversizedBinaryPingCase2_5()
    {
        byte[] bytes = new byte[126];
        Arrays.fill(bytes,(byte)0x00);

        ByteBuffer expected = ByteBuffer.allocate(bytes.length + Generator.MAX_HEADER_LENGTH);

        byte b;

        // fin + op
        b = 0x00;
        b |= 0x80; // fin on
        b |= 0x09; // ping
        expected.put(b);

        // mask + len
        b = 0x00;
        b |= 0x00; // no masking
        b |= 0x7E; // 2 byte len
        expected.put(b);

        // 2 byte len
        expected.putChar((char)bytes.length);

        // payload
        expected.put(bytes);

        expected.flip();

        UnitParser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parseQuietly(expected);

        Assert.assertEquals("error should be returned for too large of ping payload",1,capture.getErrorCount(ProtocolException.class));
    }
