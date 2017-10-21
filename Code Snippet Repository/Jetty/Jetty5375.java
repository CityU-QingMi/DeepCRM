    @Test
    public void testToBuffer_ArrayOffsetLength()
    {
        byte arr[] = new byte[128];
        Arrays.fill(arr,(byte)0xFF); // fill whole thing with FF
        int offset = 10;
        int length = 100;
        Arrays.fill(arr,offset,offset + length,(byte)0x77); // fill partial with 0x77
        ByteBuffer buf = BufferUtil.toBuffer(arr,offset,length);

        int count = 0;
        while (buf.remaining() > 0)
        {
            byte b = buf.get();
            Assert.assertEquals(b,0x77);
            count++;
        }

        Assert.assertEquals("Count of bytes",length,count);
    }
