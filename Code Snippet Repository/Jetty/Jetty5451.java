    @Test
    public void testRemoveObjectObject_NullInput()
    {
        Object input = null;

        assertNull(LazyList.remove(input,null));
        assertNull(LazyList.remove(input,"a"));
        assertNull(LazyList.remove(input,new ArrayList<Object>()));
        assertNull(LazyList.remove(input,Integer.valueOf(42)));
    }
