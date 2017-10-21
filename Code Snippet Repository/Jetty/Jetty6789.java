    @Test
    public void testCase7_3_4GenerateCloseWithStatusReason()
    {
        String message = "bad cough";
        byte[] messageBytes = message.getBytes();

        CloseInfo close = new CloseInfo(1000,message);

        ByteBuffer actual = UnitGenerator.generate(close.asFrame());

        ByteBuffer expected = ByteBuffer.allocate(32);

        expected.put(new byte[]
                { (byte)0x88 });

        byte b = 0x00; // no masking
        b |= (message.length() + 2) & 0x7F;
        expected.put(b);
        expected.putShort((short)1000);
        expected.put(messageBytes);

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
