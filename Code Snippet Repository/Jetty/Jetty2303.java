    @Test
    public void testResourceReference () throws Exception
    {
        RefAddr refAddr = new StringRefAddr("val", "10");
        Reference ref = new Reference(SomeObject.class.getName(), refAddr, SomeObjectFactory.class.getName(), null);

        InitialContext icontext = new InitialContext();
        Resource resource = new Resource (null, "resourceByRef", ref);
        NamingEntry ne = NamingEntryUtil.lookupNamingEntry(null, "resourceByRef");
        assertNotNull(ne);
        assertTrue (ne instanceof Resource);

        Object o = icontext.lookup("resourceByRef");
        assertNotNull (o);
        assertTrue (o instanceof SomeObject);

        assertEquals(((SomeObject)o).getValue(), 10);
    }
