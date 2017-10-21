    @Test
    public void testGetBestDirectBuffer() throws Exception
    {
        Assert.assertEquals(1,trie.getBest(BufferUtil.toDirectBuffer("xhelloxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(BufferUtil.toDirectBuffer("xhelxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(BufferUtil.toDirectBuffer("xhellxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(BufferUtil.toDirectBuffer("xfoo-barxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(BufferUtil.toDirectBuffer("xhell4xxxx"),1,8).intValue()); 
        
        Assert.assertEquals(1,trie.getBest(BufferUtil.toDirectBuffer("xHELLOxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(BufferUtil.toDirectBuffer("xHELxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(BufferUtil.toDirectBuffer("xHELLxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(BufferUtil.toDirectBuffer("xfoo-BARxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(BufferUtil.toDirectBuffer("xHELL4xxxx"),1,8).intValue());  
        Assert.assertEquals(9,trie.getBest(BufferUtil.toDirectBuffer("xZZZZZxxxx"),1,8).intValue());  
        
        ByteBuffer buffer = (ByteBuffer)BufferUtil.toDirectBuffer("xhelloxxxxxxx").position(2);
        Assert.assertEquals(1,trie.getBest(buffer,-1,10).intValue());
    }
