    @Test
    public void testPutAll_nullKeyInBoth_SmallOriginal() throws Exception {
        final SortedArrayStringMap original = new SortedArrayStringMap();
        original.putValue(null, "nullORIG");
        original.putValue("a", "aORIG");
        original.putValue("b", "bORIG");

        final SortedArrayStringMap other = new SortedArrayStringMap();
        other.putValue(null, "nullNEW");
        other.putValue("1", "11");
        other.putValue("2", "22");
        other.putValue("3", "33");
        other.putValue("a", "aa");
        original.putAll(other);

        assertEquals("size after put other", 6, original.size());
        assertEquals("aa", original.getValue("a"));
        assertEquals("bORIG", original.getValue("b"));
        assertEquals("11", original.getValue("1"));
        assertEquals("22", original.getValue("2"));
        assertEquals("33", original.getValue("3"));
        assertEquals("nullNEW", original.getValue(null));
    }
