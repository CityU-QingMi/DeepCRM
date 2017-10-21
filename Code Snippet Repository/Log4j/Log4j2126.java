    @Test
    public void testPutAllSelfDoesNotModify() {
        final JdkMapAdapterStringMap original = new JdkMapAdapterStringMap();
        original.putValue("a", "aaa");
        original.putValue("b", "bbb");
        original.putValue("c", "ccc");
        assertEquals("size", 3, original.size());

        // putAll with self
        original.putAll(original);
        assertEquals("size after put empty", 3, original.size());
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));
    }
