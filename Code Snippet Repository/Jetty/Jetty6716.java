    @Test
    public void testSingleMaskedTextMessage()
    {
        WebSocketFrame text = new TextFrame().setPayload("Hello");
        text.setMask(new byte[]
                { 0x37, (byte)0xfa, 0x21, 0x3d });

        ByteBuffer actual = UnitGenerator.generate(text);

        ByteBuffer expected = ByteBuffer.allocate(11);
        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // A single-frame masked text message
        expected.put(new byte[]
                { (byte)0x81, (byte)0x85, 0x37, (byte)0xfa, 0x21, 0x3d, 0x7f, (byte)0x9f, 0x4d, 0x51, 0x58 });
        expected.flip(); // make readable

        ByteBufferAssert.assertEquals("masked text buffers are not equal",expected,actual);
    }
