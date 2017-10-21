    @Test
    public void testGenerate125ByteTextCase1_1_2()
    {
        int length = 125;
        byte buf[] = new byte[length];
        Arrays.fill(buf,(byte)'*');
        String text = new String(buf,StandardCharsets.UTF_8);

        Frame textFrame = new TextFrame().setPayload(text);

        ByteBuffer actual = UnitGenerator.generate(textFrame);

        ByteBuffer expected = ByteBuffer.allocate(length + 5);

        expected.put(new byte[]
        { (byte)0x81 });

        byte b = 0x00; // no masking
        b |= length & 0x7F;
        expected.put(b);

        for (int i = 0; i < length; ++i)
        {
            expected.put("*".getBytes());
        }

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
