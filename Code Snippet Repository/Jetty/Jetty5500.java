    @Test
    public void testRemoveFromArray_SingleInput() {
        String input[] = new String[] { "a" };

        String arr[] = ArrayUtil.removeFromArray(input,null);
        assertNotNull("Should not be null", arr);
        assertEquals(1, arr.length);
        assertEquals("a", arr[0]);

        // Remove actual item
        arr = ArrayUtil.removeFromArray(input,"a");
        assertNotNull("Should not be null", arr);
        assertEquals(0, arr.length);
    }
