    @Test
    public void testPutAll_KeepsExistingValues() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "bbb");
        original.putValue("c", "ccc");
        assertEquals("size", 3, original.size());

        // add empty context data
        original.putAll(new SortedArrayStringMap());
        assertEquals("size after put empty", 3, original.size());
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));

        final SortedArrayStringMap other = new SortedArrayStringMap();
        other.putValue("1", "111");
        other.putValue("2", "222");
        other.putValue("3", "333");
        original.putAll(other);

        assertEquals("size after put other", 6, original.size());
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));
        assertEquals("111", original.getValue("1"));
        assertEquals("222", original.getValue("2"));
        assertEquals("333", original.getValue("3"));
    }
