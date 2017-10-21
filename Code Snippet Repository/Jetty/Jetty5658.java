    @Test
    public void testGetBestBuffer() throws Exception
    {
        Assert.assertEquals(1,trie.getBest(BufferUtil.toBuffer("xhelloxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(BufferUtil.toBuffer("xhelxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(BufferUtil.toBuffer("xhellxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(BufferUtil.toBuffer("xfoo-barxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(BufferUtil.toBuffer("xhell4xxxx"),1,8).intValue()); 
        
        Assert.assertEquals(1,trie.getBest(BufferUtil.toBuffer("xHELLOxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(BufferUtil.toBuffer("xHELxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(BufferUtil.toBuffer("xHELLxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(BufferUtil.toBuffer("xfoo-BARxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(BufferUtil.toBuffer("xHELL4xxxx"),1,8).intValue());  
        Assert.assertEquals(9,trie.getBest(BufferUtil.toBuffer("xZZZZZxxxx"),1,8).intValue());  
        
        ByteBuffer buffer = (ByteBuffer)BufferUtil.toBuffer("xhelloxxxxxxx").position(2);
        Assert.assertEquals(1,trie.getBest(buffer,-1,10).intValue());
    }
