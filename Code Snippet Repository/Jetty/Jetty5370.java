    @Test
    public void testPutHexInt() throws Exception
    {
        int val[] =
        {
            0,42,43,-44,-45,-2147483648,2147483647
        };

        String str[] =
        {
            "0","2A","2B","-2C","-2D","-80000000","7FFFFFFF"
        };

        ByteBuffer buffer = ByteBuffer.allocate(50);

        for (int i=0;i<val.length;i++)
        {
            BufferUtil.clearToFill(buffer);
            BufferUtil.putHexInt(buffer,val[i]);
            BufferUtil.flipToFlush(buffer,0);
            assertEquals("t"+i,str[i],BufferUtil.toString(buffer));
        }
    }
