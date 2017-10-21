    @Test
    public void testPutLong() throws Exception
    {
        long val[] =
        {
                0L,42L,43L,-44L,-45L,Long.MIN_VALUE,Long.MAX_VALUE
        };

        String str[] =
        {
                "0","42","43","-44","-45",""+Long.MIN_VALUE,""+Long.MAX_VALUE
        };

        ByteBuffer buffer = ByteBuffer.allocate(50);

        for (int i=0;i<val.length;i++)
        {
            BufferUtil.clearToFill(buffer);
            BufferUtil.putDecLong(buffer,val[i]);
            BufferUtil.flipToFlush(buffer,0);
            assertEquals("t"+i,str[i],BufferUtil.toString(buffer));
        }
    }
