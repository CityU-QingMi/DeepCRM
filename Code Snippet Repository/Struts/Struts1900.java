    public void testStringToList() {
        List list = interceptor.stringToList("foo");
        assertNotNull(list);
        assertEquals(1, list.size());

        list = interceptor.stringToList("foo,bar");
        assertEquals(2, list.size());
        assertEquals("foo", (String)list.get(0));

        list = interceptor.stringToList("foo, bar");
        assertEquals(2, list.size());
        assertEquals("bar", (String)list.get(1));

        list = interceptor.stringToList("foo  , bar");
        assertEquals(2, list.size());
        assertEquals("bar", (String)list.get(1));
    }
