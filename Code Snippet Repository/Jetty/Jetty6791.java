    @Test
    public void testCase7_3_5GenerateCloseWithStatusMaxReason()
    {
        StringBuilder message = new StringBuilder();
        for ( int i = 0 ; i < 123 ; ++i )
        {
            message.append("*");
        }

        CloseInfo close = new CloseInfo(1000,message.toString());

        ByteBuffer actual = UnitGenerator.generate(close.asFrame());
        ByteBuffer expected = ByteBuffer.allocate(132);

        byte messageBytes[] = message.toString().getBytes(StandardCharsets.UTF_8);

        expected.put(new byte[]
                { (byte)0x88 });

        byte b = 0x00; // no masking
        b |= (messageBytes.length + 2) & 0x7F;
        expected.put(b);
        expected.putShort((short)1000);

        expected.put(messageBytes);

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
