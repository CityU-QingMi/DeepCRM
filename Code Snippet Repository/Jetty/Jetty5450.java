    @Test
    public void testEnsureSize_Growth_LinkedList()
    {
        Assume.assumeTrue(STRICT); // Only run in STRICT mode.

        // Using LinkedList concrete type as LazyList internal
        // implementation has not historically looked for this
        // specifically.
        List<String> l = new LinkedList<String>();
        l.add("a");
        l.add("b");
        l.add("c");

        // NOTE: Testing for object equality might be viewed as
        //       fragile by most developers, however, for this
        //       specific implementation, we don't want the
        //       provided list to change if the size requirements
        //       have been met.

        // Trigger growth
        Object ret = LazyList.ensureSize(l,10);
        assertTrue("Should have returned a new list object", ret != l);

        // Growth not neeed.
        ret = LazyList.ensureSize(l,1);
        assertTrue("Should have returned same list object", ret == l);
    }
