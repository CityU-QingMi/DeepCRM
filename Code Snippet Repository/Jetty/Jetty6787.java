    @Test
    public void testCase7_3_3GenerateCloseWithStatus()
    {
        CloseInfo close = new CloseInfo(1000);

        ByteBuffer actual = UnitGenerator.generate(close.asFrame());

        ByteBuffer expected = ByteBuffer.allocate(5);

        expected.put(new byte[]
                { (byte)0x88, (byte)0x02, 0x03, (byte)0xe8 });

        expected.flip();

        ByteBufferAssert.assertEquals("buffers do not match",expected,actual);
    }
