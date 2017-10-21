    @Test
    public void testToBuffer_Array()
    {
        byte arr[] = new byte[128];
        Arrays.fill(arr,(byte)0x44);
        ByteBuffer buf = BufferUtil.toBuffer(arr);

        int count = 0;
        while (buf.remaining() > 0)
        {
            byte b = buf.get();
            Assert.assertEquals(b,0x44);
            count++;
        }

        Assert.assertEquals("Count of bytes",arr.length,count);
    }
