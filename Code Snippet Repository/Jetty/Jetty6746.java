    @Test
    public void testGenerate127ByteTextCase1_1_4()
    {
        int length = 127;

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; ++i)
        {
            builder.append("*");
        }

        WebSocketFrame textFrame = new TextFrame().setPayload(builder.toString());

        ByteBuffer actual = UnitGenerator.generate(textFrame);

        ByteBuffer expected = ByteBuffer.allocate(length + 5);

        expected.put(new byte[]
        { (byte)0x81 });

        byte b = 0x00; // no masking
        b |= length & 0x7E;
        expected.put(b);

        // expected.put((byte)((length>>8) & 0xFF));
        // expected.put((byte)(length & 0xFF));
        expected.putShort((short)length);

        for (int i = 0; i < length; ++i)
        {
            expected.put("*".getBytes());
        }

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
