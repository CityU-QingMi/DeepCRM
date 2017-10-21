    @Test
    public void testLink () throws Exception
    {
        ScopeA scope = new ScopeA();
        InitialContext icontext = new InitialContext();
        Link link = new Link ("linked-resourceA", "resourceB");
        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(null, "linked-resourceA");
        assertNotNull(ne);
        assertTrue(ne instanceof Link);
        assertEquals(icontext.lookup("linked-resourceA"), "resourceB");

        link = new Link (scope, "jdbc/linked-resourceX", "jdbc/linked-resourceY");
        ne = NamingEntryUtil.lookupNamingEntry(scope, "jdbc/linked-resourceX");
        assertNotNull(ne);
        assertTrue(ne instanceof Link);
    }
