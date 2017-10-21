    @Test
    public void testRemoveFromArray_MultiInput() {
        String input[] = new String[] { "a", "b", "c" };

        String arr[] = ArrayUtil.removeFromArray(input,null);
        assertNotNull("Should not be null", arr);
        assertEquals(3, arr.length);
        assertEquals("a", arr[0]);
        assertEquals("b", arr[1]);
        assertEquals("c", arr[2]);

        // Remove an actual item
        arr = ArrayUtil.removeFromArray(input,"b");
        assertNotNull("Should not be null", arr);
        assertEquals(2, arr.length);
        assertEquals("a", arr[0]);
        assertEquals("c", arr[1]);
    }
