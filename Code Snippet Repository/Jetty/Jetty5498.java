    @Test
    public void testAddToArray_SingleInput_NullItem()
    {
        String input[] = new String[] { "z" };

        String arr[] = ArrayUtil.addToArray(input,null,Object.class);
        assertNotNull(arr);
        if(STRICT) {
            // Should a null item be added to an array?
            assertEquals(1, arr.length);
        } else {
            assertEquals(2, arr.length);
            assertEquals("z", arr[0]);
            assertEquals(null, arr[1]);
        }
    }
