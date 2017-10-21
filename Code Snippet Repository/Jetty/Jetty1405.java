    @Test
    public void testGetValues() throws Exception
    {
        HttpFields fields = new HttpFields();

        fields.put("name0", "value0A,value0B");
        fields.add("name0", "value0C,value0D");
        fields.put("name1", "value1A, \"value\t, 1B\" ");
        fields.add("name1", "\"value1C\",\tvalue1D");

        Enumeration<String> e = fields.getValues("name0");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0A,value0B");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0C,value0D");
        assertEquals(false, e.hasMoreElements());

        e = fields.getValues("name0",",");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0A");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0B");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0C");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value0D");
        assertEquals(false, e.hasMoreElements());

        e = fields.getValues("name1",",");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value1A");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value\t, 1B");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value1C");
        assertEquals(true, e.hasMoreElements());
        assertEquals(e.nextElement(), "value1D");
        assertEquals(false, e.hasMoreElements());
    }
