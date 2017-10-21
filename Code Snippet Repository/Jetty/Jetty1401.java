    @Test
    public void testQualityContainsList() throws Exception
    {
        HttpField field;
        
        field = new HttpField("name","yes");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));

        field = new HttpField("name",",yes,");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","other,yes,other");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","other,  yes  ,other");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","other,  y s  ,other");
        assertTrue(field.contains("y s"));
        assertFalse(field.contains("no"));

        field = new HttpField("name","other,  \"yes\"  ,other");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","other,  \"\\\"yes\\\"\"  ,other");
        assertTrue(field.contains("\"yes\""));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name",";no,yes,;no");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));

        field = new HttpField("name","no;q=0,yes;q=1,no; q = 0");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","no;q=0.0000,yes;q=0.0001,no; q = 0.00000");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
        
        field = new HttpField("name","no;q=0.0000,Yes;Q=0.0001,no; Q = 0.00000");
        assertTrue(field.contains("yes"));
        assertFalse(field.contains("no"));
       
    }
