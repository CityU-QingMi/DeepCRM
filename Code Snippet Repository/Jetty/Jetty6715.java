    @Test
    public void testSingleMaskedPongRequest()
    {
        PongFrame pong = new PongFrame().setPayload("Hello");
        pong.setMask(new byte[]
                { 0x37, (byte)0xfa, 0x21, 0x3d });

        ByteBuffer actual = UnitGenerator.generate(pong);

        ByteBuffer expected = ByteBuffer.allocate(11);
        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // Unmasked Pong request
        expected.put(new byte[]
                { (byte)0x8a, (byte)0x85, 0x37, (byte)0xfa, 0x21, 0x3d, 0x7f, (byte)0x9f, 0x4d, 0x51, 0x58 });
        expected.flip(); // make readable

        ByteBufferAssert.assertEquals("pong buffers are not equal",expected,actual);
    }
