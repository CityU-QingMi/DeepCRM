    @Test
    public void testGenerateEmptyBinaryCase1_2_1()
    {
        WebSocketFrame binaryFrame = new BinaryFrame().setPayload(new byte[] {});

        ByteBuffer actual = UnitGenerator.generate(binaryFrame);

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x82, (byte)0x00 });

        BufferUtil.flipToFlush(expected,0);

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
