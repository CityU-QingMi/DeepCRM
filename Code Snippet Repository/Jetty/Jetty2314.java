    @Test
    public void testLookupNamingEntries() throws Exception
    {
        ScopeA scope = new ScopeA();
        List list = NamingEntryUtil.lookupNamingEntries(scope, MyNamingEntry.class);
        assertTrue(list.isEmpty());

        MyNamingEntry mne1 = new MyNamingEntry(scope, "a/b", 1);
        MyNamingEntry mne2 = new MyNamingEntry(scope, "a/c", 2);

        ScopeA scope2 = new ScopeA();
        MyNamingEntry mne3 = new MyNamingEntry(scope2, "a/b", 3);

        list = NamingEntryUtil.lookupNamingEntries(scope, MyNamingEntry.class);
        assertEquals(2, list.size());
        assertTrue (list.contains(mne1));
        assertTrue (list.contains(mne2));

        list = NamingEntryUtil.lookupNamingEntries(scope2, MyNamingEntry.class);
        assertEquals(1, list.size());
        assertTrue(list.contains(mne3));
    }
