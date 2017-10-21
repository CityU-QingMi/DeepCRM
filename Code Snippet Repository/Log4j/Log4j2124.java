    @Test
    public void testPutAll_sizePowerOfTwo() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "bbb");
        original.putValue("c", "ccc");
        original.putValue("d", "ddd");
        assertEquals("size", 4, original.size());

        // add empty context data
        original.putAll(new JdkMapAdapterStringMap());
        assertEquals("size after put empty", 4, original.size());
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));
        assertEquals("ddd", original.getValue("d"));

        final JdkMapAdapterStringMap other = new JdkMapAdapterStringMap();
        other.putValue("1", "111");
        other.putValue("2", "222");
        other.putValue("3", "333");
        other.putValue("4", "444");
        original.putAll(other);

        assertEquals("size after put other", 8, original.size());
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));
        assertEquals("ddd", original.getValue("d"));
        assertEquals("111", original.getValue("1"));
        assertEquals("222", original.getValue("2"));
        assertEquals("333", original.getValue("3"));
        assertEquals("444", original.getValue("4"));
    }
