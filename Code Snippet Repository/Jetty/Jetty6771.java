    @Test
    public void testGenerateHelloPingCase2_2()
    {
        String message = "Hello, world!";
        byte[] messageBytes = StringUtil.getUtf8Bytes(message);

        PingFrame pingFrame = new PingFrame().setPayload(messageBytes);

        ByteBuffer actual = UnitGenerator.generate(pingFrame);

        ByteBuffer expected = ByteBuffer.allocate(32);

        expected.put(new byte[]
                { (byte)0x89 });

        byte b = 0x00; // no masking
        b |= messageBytes.length & 0x7F;
        expected.put(b);
        expected.put(messageBytes);

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
