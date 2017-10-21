    @Test
    public void testSingleUnmasked256ByteBinaryMessage()
    {
        int dataSize = 256;

        BinaryFrame binary = new BinaryFrame();
        byte payload[] = new byte[dataSize];
        Arrays.fill(payload,(byte)0x44);
        binary.setPayload(ByteBuffer.wrap(payload));

        ByteBuffer actual = UnitGenerator.generate(binary);

        ByteBuffer expected = ByteBuffer.allocate(dataSize + FUDGE);
        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // 256 bytes binary message in a single unmasked frame
        expected.put(new byte[]
                { (byte)0x82, (byte)0x7E });
        expected.putShort((short)0x01_00);

        for (int i = 0; i < dataSize; i++)
        {
            expected.put((byte)0x44);
        }

        expected.flip();

        ByteBufferAssert.assertEquals("binary buffers are not equal",expected,actual);
    }
