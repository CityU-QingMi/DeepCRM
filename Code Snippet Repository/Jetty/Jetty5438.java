    @Test
    public void testAddObjectObject_NonListInput()
    {
        String input = "a";

        Object list = LazyList.add(input, "b");
        assertNotNull(list);
        assertTrue(list instanceof List);
        assertEquals(2,LazyList.size(list));
    }
