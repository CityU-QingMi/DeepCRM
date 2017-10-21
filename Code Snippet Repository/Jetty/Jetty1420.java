    @Test
    public void testAdd() throws Exception
    {
        HttpFields fields = new HttpFields();

        fields.add("name0", "value0");
        fields.add("name1", "valueA");
        fields.add("name2", "value2");

        assertEquals("value0",fields.get("name0"));
        assertEquals("valueA",fields.get("name1"));
        assertEquals("value2",fields.get("name2"));

        fields.add("name1", "valueB");

        assertEquals("value0",fields.get("name0"));
        assertEquals("valueA",fields.get("name1"));
        assertEquals("value2",fields.get("name2"));
        assertNull(fields.get("name3"));

        int matches=0;
        Enumeration<String> e = fields.getFieldNames();
        while (e.hasMoreElements())
        {
            Object o=e.nextElement();
            if ("name0".equals(o))
                matches++;
            if ("name1".equals(o))
                matches++;
            if ("name2".equals(o))
                matches++;
        }
        assertEquals(3, matches);

        e = fields.getValues("name1");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "valueA");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "valueB");
        assertEquals(false, e.hasMoreElements());
    }
