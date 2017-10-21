    @Test
    public void testGetString() throws Exception
    {
        Assert.assertEquals(1,trie.get("hello").intValue());
        Assert.assertEquals(2,trie.get("He").intValue());
        Assert.assertEquals(3,trie.get("HELL").intValue());
        Assert.assertEquals(4,trie.get("wibble").intValue());
        Assert.assertEquals(5,trie.get("Wobble").intValue());
        Assert.assertEquals(6,trie.get("foo-bar").intValue());
        Assert.assertEquals(7,trie.get("foo+bar").intValue());
        
        Assert.assertEquals(1,trie.get("Hello").intValue());
        Assert.assertEquals(2,trie.get("HE").intValue());
        Assert.assertEquals(3,trie.get("heLL").intValue());
        Assert.assertEquals(4,trie.get("Wibble").intValue());
        Assert.assertEquals(5,trie.get("wobble").intValue());
        Assert.assertEquals(6,trie.get("Foo-bar").intValue());
        Assert.assertEquals(7,trie.get("FOO+bar").intValue());
        Assert.assertEquals(8,trie.get("HELL4").intValue());
        Assert.assertEquals(9,trie.get("").intValue());
        
        Assert.assertEquals(null,trie.get("helloworld"));
        Assert.assertEquals(null,trie.get("Help"));
        Assert.assertEquals(null,trie.get("Blah"));
    }
