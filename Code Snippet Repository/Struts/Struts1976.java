    public void testPutGet() {
        PrefixTrie trie = new PrefixTrie();
        Object foo = new Object();
        trie.put("foo:", foo);
        Object a = new Object();
        trie.put("a:", a);
        assertSame(foo, trie.get("foo:bar"));
        assertSame(a, trie.get("a:bar"));
        assertNull(trie.get("tee:bar"));
        assertNull(trie.get("foobar"));
    }
