    @Test
    public void testEnvEntryNoScope() throws Exception
    {
        EnvEntry ee = new EnvEntry("nameZ", "zstring", true);
        List list = NamingEntryUtil.lookupNamingEntries(null, EnvEntry.class);
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        Object o = list.get(0);
        assertTrue (o instanceof EnvEntry);
        EnvEntry eo = (EnvEntry)o;
        assertEquals ("nameZ", eo.getJndiName());
    }
