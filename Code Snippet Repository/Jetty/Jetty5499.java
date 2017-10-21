    @Test
    public void testAddToArray_SingleInput_SimpleItem()
    {
        String input[] = new String[] { "z" };

        String arr[] = ArrayUtil.addToArray(input,"a",String.class);
        assertNotNull(arr);
        assertEquals(2, arr.length);
        assertEquals("z", arr[0]);
        assertEquals("a", arr[1]);
    }
