    @Test
    public void testNullObject () throws Exception
    {
        InitialContext icontext = new InitialContext();
        Resource resource = new Resource ("foo/bar", null);
        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(null, "foo/bar");
        assertNotNull(ne);
        Object o = icontext.lookup("foo/bar");
        assertNull(o);

    }
