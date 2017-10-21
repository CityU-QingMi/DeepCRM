    @Test
    public void testToArray_GenericListInput() {
        List<String> input = new ArrayList<String>();
        input.add("a");
        input.add("b");
        input.add("c");

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
