    @Test
    public void testStaticHuffmanValues()
    {
        HpackContext ctx = new HpackContext(4096);
        for (int i=2;i<=14;i++)
        {
            Entry entry=ctx.get(i);
            assertTrue(entry.isStatic());
            
            ByteBuffer buffer = ByteBuffer.wrap(entry.getStaticHuffmanValue());
            int huff = 0xff&buffer.get();
            assertTrue((0x80&huff)==0x80);
            
            int len = NBitInteger.decode(buffer,7);
            
            assertEquals(len,buffer.remaining());
            String value = Huffman.decode(buffer);
            
            assertEquals(entry.getHttpField().getValue(),value);
            
        }
    }
