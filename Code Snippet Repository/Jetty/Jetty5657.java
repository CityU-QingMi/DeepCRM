    @Test
    public void testGetBestArray() throws Exception
    {
        Assert.assertEquals(1,trie.getBest(StringUtil.getUtf8Bytes("xhelloxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(StringUtil.getUtf8Bytes("xhelxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(StringUtil.getUtf8Bytes("xhellxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(StringUtil.getUtf8Bytes("xfoo-barxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(StringUtil.getUtf8Bytes("xhell4xxxx"),1,8).intValue()); 
        
        Assert.assertEquals(1,trie.getBest(StringUtil.getUtf8Bytes("xHELLOxxxx"),1,8).intValue());
        Assert.assertEquals(2,trie.getBest(StringUtil.getUtf8Bytes("xHELxoxxxx"),1,8).intValue());
        Assert.assertEquals(3,trie.getBest(StringUtil.getUtf8Bytes("xHELLxxxxx"),1,8).intValue()); 
        Assert.assertEquals(6,trie.getBest(StringUtil.getUtf8Bytes("xfoo-BARxx"),1,8).intValue()); 
        Assert.assertEquals(8,trie.getBest(StringUtil.getUtf8Bytes("xHELL4xxxx"),1,8).intValue());  
        Assert.assertEquals(9,trie.getBest(StringUtil.getUtf8Bytes("xZZZZZxxxx"),1,8).intValue());  
    }
