    @Test
    public void testSingleUnmaskedTextMessage()
    {
        WebSocketFrame text = new TextFrame().setPayload("Hello");

        ByteBuffer actual = UnitGenerator.generate(text);

        ByteBuffer expected = ByteBuffer.allocate(10);

        expected.put(new byte[]
                { (byte)0x81, (byte)0x05, (byte)0x48, (byte)0x65, (byte)0x6c, (byte)0x6c, (byte)0x6f });

        expected.flip();

        ByteBufferAssert.assertEquals("t1 buffers are not equal",expected,actual);
    }
