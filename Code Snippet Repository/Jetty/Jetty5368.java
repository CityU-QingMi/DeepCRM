    @Test
    public void testPutInt() throws Exception
    {
        int val[] =
        {
            0,42,43,-44,-45,Integer.MIN_VALUE,Integer.MAX_VALUE
        };

        String str[] =
        {
            "0","42","43","-44","-45",""+Integer.MIN_VALUE,""+Integer.MAX_VALUE
        };

        ByteBuffer buffer = ByteBuffer.allocate(24);

        for (int i=0;i<val.length;i++)
        {
            BufferUtil.clearToFill(buffer);
            BufferUtil.putDecInt(buffer,val[i]);
            BufferUtil.flipToFlush(buffer,0);
            assertEquals("t"+i,str[i],BufferUtil.toString(buffer));
        }
    }
