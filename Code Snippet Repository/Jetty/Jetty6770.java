    @Test
    public void testGenerateEmptyPingCase2_1()
    {
        WebSocketFrame pingFrame = new PingFrame();

        ByteBuffer actual = UnitGenerator.generate(pingFrame);

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x89, (byte)0x00 });

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
