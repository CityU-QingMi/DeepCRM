    @Test
    public void testGenerate65535ByteBinaryCase1_2_6()
    {
        int length = 65535;

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
        b |= 0x7E;
        expected.put(b);
        expected.put(new byte[]{ (byte)0xff, (byte)0xff});

        for ( int i = 0 ; i < length ; ++i )
        {
            expected.put("*".getBytes());
        }

        BufferUtil.flipToFlush(expected,0);

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
