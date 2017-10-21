    @Test
    public void testGenerate127ByteBinaryCase1_2_4()
    {
        int length = 127;

        ByteBuffer bb = ByteBuffer.allocate(length);

        for ( int i = 0 ; i < length ; ++i)
        {
            bb.put("*".getBytes());

        }

        bb.flip();

        WebSocketFrame binaryFrame = new BinaryFrame().setPayload(bb);

        ByteBuffer actual = UnitGenerator.generate(binaryFrame);

        ByteBuffer expected = ByteBuffer.allocate(length + 5);

        expected.put(new byte[]
                { (byte)0x82 });

        byte b = 0x00; // no masking
        b |= length & 0x7E;
        expected.put(b);

        //expected.put((byte)((length>>8) & 0xFF));
        //expected.put((byte)(length & 0xFF));
        expected.putShort((short)length);

        for ( int i = 0 ; i < length ; ++i )
        {
            expected.put("*".getBytes());
        }

        BufferUtil.flipToFlush(expected,0);

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
