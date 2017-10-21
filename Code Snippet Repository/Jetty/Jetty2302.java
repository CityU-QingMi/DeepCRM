    @Test
    public void testResourceReferenceable() throws Exception
    {
        SomeOtherObject someOtherObj = new SomeOtherObject("100");
        InitialContext icontext = new InitialContext();
        Resource res = new Resource("resourceByReferenceable", someOtherObj);
        Object o = icontext.lookup("resourceByReferenceable");
        assertNotNull(o);
        assertTrue (o instanceof SomeOtherObject);
        assertEquals(((SomeOtherObject)o).getValue(), 100);
    }
