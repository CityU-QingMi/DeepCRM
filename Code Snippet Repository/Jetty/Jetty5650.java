    @Test 
    public void testFull() throws Exception
    {
       if (!(trie instanceof ArrayTrie<?> || trie instanceof ArrayTernaryTrie<?>))
           return;
       
       Assert.assertFalse(trie.put("Large: This is a really large key and should blow the maximum size of the array trie as lots of nodes should already be used.",99));
       testGetString();
       testGetBestArray();
       testGetBestBuffer();
    }
