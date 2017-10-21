    @Test
    public void testRemovePut() throws Exception
    {
        HttpFields header = new HttpFields(1);

        header.put("name0", "value0");
        header.put("name1", "value1");
        header.put("name2", "value2");

        assertEquals("value0",header.get("name0"));
        assertEquals("value1",header.get("name1"));
        assertEquals("value2",header.get("name2"));

        header.remove("name1");

        assertEquals("value0",header.get("name0"));
        assertNull(header.get("name1"));
        assertEquals("value2",header.get("name2"));
        assertNull(header.get("name3"));

        int matches=0;
        Enumeration<String> e = header.getFieldNames();
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
        assertEquals(2, matches);

        e = header.getValues("name1");
        assertEquals(false, e.hasMoreElements());
    }
