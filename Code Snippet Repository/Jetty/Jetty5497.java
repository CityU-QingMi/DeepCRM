    @Test
    public void testAddToArray_EmptyInput_SimpleItem()
    {
        String input[] = new String[0];

        String arr[] = ArrayUtil.addToArray(input,"a",String.class);
        assertNotNull(arr);
        assertEquals(1, arr.length);
        assertEquals("a", arr[0]);
    }
