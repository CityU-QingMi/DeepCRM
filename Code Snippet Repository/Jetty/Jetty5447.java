    @Test
    public void testEnsureSize_GenericListInput_LinkedList()
    {
        Assume.assumeTrue(STRICT); // Only run in STRICT mode.

        // Using LinkedList concrete type as LazyList internal
        // implementation does not look for this specifically.
        List<String> input = new LinkedList<String>();
        input.add("a");
        input.add("b");

        Object list = LazyList.ensureSize(input,10);
        assertNotNull(list);
        assertTrue(list instanceof List);
        // Not possible to test for List capacity value.
        assertEquals(2, LazyList.size(list));
        assertEquals("a", LazyList.get(list,0));
        assertEquals("b", LazyList.get(list,1));
    }
