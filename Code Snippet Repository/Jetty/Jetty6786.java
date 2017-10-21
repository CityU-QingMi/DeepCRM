    @Test
    public void testCase7_3_2Parse1BytePayloadClose()
    {
        ByteBuffer expected = Hex.asByteBuffer("880100");

        UnitParser parser = new UnitParser(policy);
        IncomingFramesCapture capture = new IncomingFramesCapture();
        parser.setIncomingFramesHandler(capture);
        parser.parseQuietly(expected);

        Assert.assertEquals("error on invalid close payload",1,capture.getErrorCount(ProtocolException.class));

        ProtocolException known = (ProtocolException)capture.getErrors().poll();

        Assert.assertThat("Payload.message",known.getMessage(),containsString("Invalid close frame payload length"));
    }
