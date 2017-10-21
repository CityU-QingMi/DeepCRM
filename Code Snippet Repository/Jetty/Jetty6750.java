    @Test
    public void testGenerateEmptyTextCase1_1_1()
    {
        WebSocketFrame textFrame = new TextFrame().setPayload("");

        ByteBuffer actual = UnitGenerator.generate(textFrame);

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
        { (byte)0x81, (byte)0x00 });

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
