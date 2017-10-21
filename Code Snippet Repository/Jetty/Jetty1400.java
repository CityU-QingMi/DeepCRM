    @Test
    public void testContainsList() throws Exception
    {
        HttpField field = new HttpField("name",",aaa,Bbb,CCC, ddd , e e, \"\\\"f,f\\\"\", ");
        assertTrue(field.contains("aaa"));
        assertTrue(field.contains("bbb"));
        assertTrue(field.contains("ccc"));
        assertTrue(field.contains("Aaa"));
        assertTrue(field.contains("Bbb"));
        assertTrue(field.contains("Ccc"));
        assertTrue(field.contains("AAA"));
        assertTrue(field.contains("BBB"));
        assertTrue(field.contains("CCC"));
        assertTrue(field.contains("ddd"));
        assertTrue(field.contains("e e"));
        assertTrue(field.contains("\"f,f\""));
        assertFalse(field.contains(""));
        assertFalse(field.contains("aa"));
        assertFalse(field.contains("bb"));
        assertFalse(field.contains("cc"));
        assertFalse(field.contains(null));
    }
