    @Test
    public void testCase7_3_1GenerateEmptyClose()
    {
        CloseInfo close = new CloseInfo();

        ByteBuffer actual = UnitGenerator.generate(close.asFrame());

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x88, (byte)0x00 });

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
