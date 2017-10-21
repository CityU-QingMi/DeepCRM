    @Test
    public void testEnvEntryNonOverride() throws Exception
    {
        ScopeA scope = new ScopeA();
        EnvEntry ee = new EnvEntry (scope, "nameA", someObject, false);

        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(scope, "nameA");
        assertNotNull(ne);
        assertTrue(ne instanceof EnvEntry);
        assertFalse (((EnvEntry)ne).isOverrideWebXml());

        Context scopeContext = NamingEntryUtil.getContextForScope(scope);
        assertNotNull(scopeContext);
        Context namingEntriesContext = NamingEntryUtil.getContextForNamingEntries(scope);
        assertNotNull(namingEntriesContext);
        assertEquals(someObject, scopeContext.lookup("nameA"));
    }
