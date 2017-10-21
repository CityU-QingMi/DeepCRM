    @Test
    public void testRePut() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("name0", "value0");
        header.put("name1", "xxxxxx");
        header.put("name2", "value2");

        assertEquals("value0",header.get("name0"));
        assertEquals("xxxxxx",header.get("name1"));
        assertEquals("value2",header.get("name2"));

        header.put("name1", "value1");

        assertEquals("value0",header.get("name0"));
        assertEquals("value1",header.get("name1"));
        assertEquals("value2",header.get("name2"));
        assertNull(header.get("name3"));

        int matches=0;
        Enumeration<String> e = header.getFieldNames();
        while (e.hasMoreElements())
        {
            String o=e.nextElement();
            if ("name0".equals(o))
                matches++;
            if ("name1".equals(o))
                matches++;
            if ("name2".equals(o))
                matches++;
        }
        assertEquals(3, matches);


        e = header.getValues("name1");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value1");
        assertEquals(false, e.hasMoreElements());
    }
