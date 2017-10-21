    @Test
    public void testContainsSimple() throws Exception
    {
        HttpField field = new HttpField("name","SomeValue");
        assertTrue(field.contains("somevalue"));
        assertTrue(field.contains("sOmEvAlUe"));
        assertTrue(field.contains("SomeValue"));
        assertFalse(field.contains("other"));
        assertFalse(field.contains("some"));
        assertFalse(field.contains("Some"));
        assertFalse(field.contains("value"));
        assertFalse(field.contains("v"));
        assertFalse(field.contains(""));
        assertFalse(field.contains(null));
    }
