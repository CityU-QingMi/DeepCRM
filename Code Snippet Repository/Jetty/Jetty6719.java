    @Test
    public void testSingleUnmaskedPingRequest() throws Exception
    {
        PingFrame ping = new PingFrame().setPayload("Hello");

        ByteBuffer actual = UnitGenerator.generate(ping);

        ByteBuffer expected = ByteBuffer.allocate(10);
        expected.put(new byte[]
                { (byte)0x89, (byte)0x05, (byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f });
        expected.flip(); // make readable

        ByteBufferAssert.assertEquals("Ping buffers",expected,actual);
    }
