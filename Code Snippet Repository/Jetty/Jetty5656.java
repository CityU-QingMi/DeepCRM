    @Test
    public void testGetDirectBuffer() throws Exception
    {
        Assert.assertEquals(1,trie.get(BufferUtil.toDirectBuffer("xhellox"),1,5).intValue());
        Assert.assertEquals(2,trie.get(BufferUtil.toDirectBuffer("xhellox"),1,2).intValue());
        Assert.assertEquals(3,trie.get(BufferUtil.toDirectBuffer("xhellox"),1,4).intValue());
        Assert.assertEquals(4,trie.get(BufferUtil.toDirectBuffer("wibble"),0,6).intValue());
        Assert.assertEquals(5,trie.get(BufferUtil.toDirectBuffer("xWobble"),1,6).intValue());
        Assert.assertEquals(6,trie.get(BufferUtil.toDirectBuffer("xfoo-barx"),1,7).intValue());
        Assert.assertEquals(7,trie.get(BufferUtil.toDirectBuffer("xfoo+barx"),1,7).intValue());
        
        Assert.assertEquals(1,trie.get(BufferUtil.toDirectBuffer("xhellox"),1,5).intValue());
        Assert.assertEquals(2,trie.get(BufferUtil.toDirectBuffer("xHELLox"),1,2).intValue());
        Assert.assertEquals(3,trie.get(BufferUtil.toDirectBuffer("xhellox"),1,4).intValue());
        Assert.assertEquals(4,trie.get(BufferUtil.toDirectBuffer("Wibble"),0,6).intValue());
        Assert.assertEquals(5,trie.get(BufferUtil.toDirectBuffer("xwobble"),1,6).intValue());
        Assert.assertEquals(6,trie.get(BufferUtil.toDirectBuffer("xFOO-barx"),1,7).intValue());
        Assert.assertEquals(7,trie.get(BufferUtil.toDirectBuffer("xFOO+barx"),1,7).intValue());

        Assert.assertEquals(null,trie.get(BufferUtil.toDirectBuffer("xHelloworldx"),1,10));
        Assert.assertEquals(null,trie.get(BufferUtil.toDirectBuffer("xHelpx"),1,4));
        Assert.assertEquals(null,trie.get(BufferUtil.toDirectBuffer("xBlahx"),1,4));
    }
