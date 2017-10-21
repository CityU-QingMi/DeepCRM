    @Test
    public void testPutAll_largeAddition() {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue(null, "nullVal");
        original.putValue("a", "aaa");
        original.putValue("b", "bbb");
        original.putValue("c", "ccc");
        original.putValue("d", "ddd");
        assertEquals("size", 5, original.size());

        final SortedArrayStringMap other = new SortedArrayStringMap();
        for (int i = 0 ; i < 500; i++) {
            other.putValue(String.valueOf(i), String.valueOf(i));
        }
        other.putValue(null, "otherVal");
        original.putAll(other);

        assertEquals("size after put other", 505, original.size());
        assertEquals("otherVal", original.getValue(null));
        assertEquals("aaa", original.getValue("a"));
        assertEquals("bbb", original.getValue("b"));
        assertEquals("ccc", original.getValue("c"));
        assertEquals("ddd", original.getValue("d"));
        for (int i = 0 ; i < 500; i++) {
            assertEquals(String.valueOf(i), original.getValue(String.valueOf(i)));
        }
    }
