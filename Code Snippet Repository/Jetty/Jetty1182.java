    @Test
    public void testEncodeExampleD_1_1()
    {
        ByteBuffer buf = BufferUtil.allocate(16);
        int p=BufferUtil.flipToFill(buf);
        buf.put((byte)0x77);
        buf.put((byte)0xFF);
        NBitInteger.encode(buf,5,10);
        BufferUtil.flipToFlush(buf,p);
        
        String r=TypeUtil.toHexString(BufferUtil.toArray(buf));
        
        assertEquals("77Ea",r);
        
    }
