    @Test
    public void testAddToArray_NullInput_SimpleItem()
    {
        Object input[] = null;

        Object arr[] = ArrayUtil.addToArray(input,"a",String.class);
        assertNotNull(arr);
        assertEquals(1, arr.length);
        assertEquals("a", arr[0]);

        // Same test, but with an undefined type
        arr = ArrayUtil.addToArray(input,"b",null);
        assertNotNull(arr);
        assertEquals(1, arr.length);
        assertEquals("b", arr[0]);
    }
