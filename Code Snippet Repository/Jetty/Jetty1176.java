    @Test
    public void testEncode() throws Exception
    {
        for (String[] test:tests)
        {
            ByteBuffer buf = BufferUtil.allocate(1024);
            int pos=BufferUtil.flipToFill(buf);
            Huffman.encode(buf,test[2]);
            BufferUtil.flipToFlush(buf,pos);
            String encoded=TypeUtil.toHexString(BufferUtil.toArray(buf)).toLowerCase(Locale.ENGLISH);
            Assert.assertEquals(test[0],test[1],encoded);
            Assert.assertEquals(test[1].length()/2,Huffman.octetsNeeded(test[2]));
        }
    }
