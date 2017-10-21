    @Test
    public void testEncodeExampleD_1_3()
    {
        ByteBuffer buf = BufferUtil.allocate(16);
        int p=BufferUtil.flipToFill(buf);
        buf.put((byte)0x88);
        buf.put((byte)0xFF);
        NBitInteger.encode(buf,8,42);
        BufferUtil.flipToFlush(buf,p);
        
        String r=TypeUtil.toHexString(BufferUtil.toArray(buf));
        
        Assert.assertEquals("88Ff2a",r);
        
    }
