    @Test
    public void testAddToArray_NullInput_NullItem()
    {
        Object input[] = null;

        Object arr[] = ArrayUtil.addToArray(input,null,Object.class);
        assertNotNull(arr);
        if(STRICT) {
            // Adding null item to array should result in nothing added?
            assertEquals(0, arr.length);
        } else {
            assertEquals(1, arr.length);
        }
    }
