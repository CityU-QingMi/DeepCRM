    @Test
    public void testPut() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("name0", "value:0");
        header.put("name1", "value1");

        assertEquals(2,header.size());
        assertEquals("value:0",header.get("name0"));
        assertEquals("value1",header.get("name1"));
        assertNull(header.get("name2"));

        int matches=0;
        Enumeration<String> e = header.getFieldNames();
        while (e.hasMoreElements())
        {
            Object o=e.nextElement();
            if ("name0".equals(o))
                matches++;
            if ("name1".equals(o))
                matches++;
        }
        assertEquals(2, matches);

        e = header.getValues("name0");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value:0");
        assertEquals(false, e.hasMoreElements());
    }
