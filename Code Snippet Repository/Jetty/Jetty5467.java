    @Test
    public void testToArray_LazyListInput() {
        Object input = LazyList.add(null, "a");
        input = LazyList.add(input, "b");
        input = LazyList.add(input, "c");

        Object arr = LazyList.toArray(input,String.class);
        assertNotNull(arr);
        assertTrue(arr.getClass().isArray());
        assertTrue(arr instanceof String[]);

        String strs[] = (String[])arr;
        assertEquals(3, strs.length);
        assertEquals("a", strs[0]);
        assertEquals("b", strs[1]);
        assertEquals("c", strs[2]);
    }
