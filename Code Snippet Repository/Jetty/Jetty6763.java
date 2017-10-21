    @Test
    public void testGenerate65536ByteBinaryCase1_2_7()
    {
        int length = 65536;

        ByteBuffer bb = ByteBuffer.allocate(length);

        for ( int i = 0 ; i < length ; ++i)
        {
            bb.put("*".getBytes());

        }

        bb.flip();

        WebSocketFrame binaryFrame = new BinaryFrame().setPayload(bb);

        ByteBuffer actual = UnitGenerator.generate(binaryFrame);

        ByteBuffer expected = ByteBuffer.allocate(length + 11);

        expected.put(new byte[]
                { (byte)0x82 });

        byte b = 0x00; // no masking
        b |= 0x7F;
        expected.put(b);
        expected.put(new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x00});


        for ( int i = 0 ; i < length ; ++i )
        {
            expected.put("*".getBytes());
        }

        BufferUtil.flipToFlush(expected,0);

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
