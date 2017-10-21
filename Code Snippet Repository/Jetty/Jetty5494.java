    @Test
    public void testAddToArray_NullNullNull()
    {
        // NPE if item && type are both null.
        Assume.assumeTrue(STRICT);

        // Harsh test case.
        Object input[] = null;

        Object arr[] = ArrayUtil.addToArray(input,null,null);
        assertNotNull(arr);
        if(STRICT) {
            // Adding null item to array should result in nothing added?
            assertEquals(0, arr.length);
        } else {
            assertEquals(1, arr.length);
        }
    }
