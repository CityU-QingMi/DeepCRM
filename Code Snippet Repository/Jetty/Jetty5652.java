    @Test
    public void testOverflow() throws Exception
    {
        int i=0;
        while (true) 
        {
            if (++i>10000)
                break; // must not be fixed size
            if (!trie.put("prefix" + i, i))
            {
                Assert.assertTrue(trie.isFull());
                break;
            }
        }
        
        Assert.assertTrue(!trie.isFull() || !trie.put("overflow", 0));
    }
