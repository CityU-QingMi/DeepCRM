    @Test
    public void testKeySet() throws Exception
    {
        Assert.assertTrue(trie.keySet().contains("hello"));
        Assert.assertTrue(trie.keySet().contains("He"));
        Assert.assertTrue(trie.keySet().contains("HELL"));
        Assert.assertTrue(trie.keySet().contains("wibble"));
        Assert.assertTrue(trie.keySet().contains("Wobble"));
        Assert.assertTrue(trie.keySet().contains("foo-bar"));
        Assert.assertTrue(trie.keySet().contains("foo+bar"));
        Assert.assertTrue(trie.keySet().contains("HELL4"));
        Assert.assertTrue(trie.keySet().contains(""));        
    }
