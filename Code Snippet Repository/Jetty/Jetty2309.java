    @Test
    public void testResource () throws Exception
    {
        InitialContext icontext = new InitialContext();

        Resource resource = new Resource (null, "resourceA/b/c", someObject);
        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(null, "resourceA/b/c");
        assertNotNull(ne);
        assertTrue(ne instanceof Resource);
        assertEquals(icontext.lookup("resourceA/b/c"), someObject);

        Object scope = new ScopeA();
        Resource resource2 = new Resource (scope, "resourceB", someObject);
        ne = NamingEntryUtil.lookupNamingEntry(scope, "resourceB");
        assertNotNull(ne);
        assertTrue(ne instanceof Resource);

        ne = NamingEntryUtil.lookupNamingEntry(null, "resourceB");
        assertNull(ne);

        ne = NamingEntryUtil.lookupNamingEntry(new ScopeB(), "resourceB");
        assertNull(ne);
        testLink();
    }
