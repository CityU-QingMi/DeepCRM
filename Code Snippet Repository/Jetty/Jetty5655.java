    @Test
    public void testGetBuffer() throws Exception
    {
        Assert.assertEquals(1,trie.get(BufferUtil.toBuffer("xhellox"),1,5).intValue());
        Assert.assertEquals(2,trie.get(BufferUtil.toBuffer("xhellox"),1,2).intValue());
        Assert.assertEquals(3,trie.get(BufferUtil.toBuffer("xhellox"),1,4).intValue());
        Assert.assertEquals(4,trie.get(BufferUtil.toBuffer("wibble"),0,6).intValue());
        Assert.assertEquals(5,trie.get(BufferUtil.toBuffer("xWobble"),1,6).intValue());
        Assert.assertEquals(6,trie.get(BufferUtil.toBuffer("xfoo-barx"),1,7).intValue());
        Assert.assertEquals(7,trie.get(BufferUtil.toBuffer("xfoo+barx"),1,7).intValue());
        
        Assert.assertEquals(1,trie.get(BufferUtil.toBuffer("xhellox"),1,5).intValue());
        Assert.assertEquals(2,trie.get(BufferUtil.toBuffer("xHELLox"),1,2).intValue());
        Assert.assertEquals(3,trie.get(BufferUtil.toBuffer("xhellox"),1,4).intValue());
        Assert.assertEquals(4,trie.get(BufferUtil.toBuffer("Wibble"),0,6).intValue());
        Assert.assertEquals(5,trie.get(BufferUtil.toBuffer("xwobble"),1,6).intValue());
        Assert.assertEquals(6,trie.get(BufferUtil.toBuffer("xFOO-barx"),1,7).intValue());
        Assert.assertEquals(7,trie.get(BufferUtil.toBuffer("xFOO+barx"),1,7).intValue());

        Assert.assertEquals(null,trie.get(BufferUtil.toBuffer("xHelloworldx"),1,10));
        Assert.assertEquals(null,trie.get(BufferUtil.toBuffer("xHelpx"),1,4));
        Assert.assertEquals(null,trie.get(BufferUtil.toBuffer("xBlahx"),1,4));
    }
