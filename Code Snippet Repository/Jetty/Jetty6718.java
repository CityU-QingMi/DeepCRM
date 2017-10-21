    @Test
    public void testSingleUnmasked64KBinaryMessage()
    {
        int dataSize = 1024 * 64;

        BinaryFrame binary = new BinaryFrame();
        byte payload[] = new byte[dataSize];
        Arrays.fill(payload,(byte)0x44);
        binary.setPayload(ByteBuffer.wrap(payload));

        ByteBuffer actual = UnitGenerator.generate(binary);

        ByteBuffer expected = ByteBuffer.allocate(dataSize + 10);
        // Raw bytes as found in RFC 6455, Section 5.7 - Examples
        // 64k bytes binary message in a single unmasked frame
        expected.put(new byte[]
                { (byte)0x82, (byte)0x7F });
        expected.putInt(0x00_00_00_00);
        expected.putInt(0x00_01_00_00);

        for (int i = 0; i < dataSize; i++)
        {
            expected.put((byte)0x44);
        }

        expected.flip();

        ByteBufferAssert.assertEquals("binary buffers are not equal",expected,actual);
    }
