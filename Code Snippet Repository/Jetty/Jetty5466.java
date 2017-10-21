    @Test
    public void testToArray_NonListInput() {
        String input = "a";

        Object arr = LazyList.toArray(input,String.class);
        assertNotNull(arr);
        assertTrue(arr.getClass().isArray());
        assertTrue(arr instanceof String[]);

        String strs[] = (String[])arr;
        assertEquals(1, strs.length);
        assertEquals("a", strs[0]);
    }
