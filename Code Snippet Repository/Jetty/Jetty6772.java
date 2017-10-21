    @Test( expected=WebSocketException.class )
    public void testGenerateOversizedBinaryPingCase2_5_B()
    {
        byte[] bytes = new byte[126];
        Arrays.fill(bytes, (byte)0x00);

        PingFrame pingFrame = new PingFrame();
        pingFrame.setPayload(ByteBuffer.wrap(bytes)); // should throw exception

        // FIXME: Remove? UnitGenerator.generate(pingFrame);
    }
