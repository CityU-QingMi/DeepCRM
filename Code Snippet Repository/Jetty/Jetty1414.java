    @Test
    public void testGet() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("name0", "value0");
        header.put("name1", "value1");

        assertEquals("value0",header.get("name0"));
        assertEquals("value0",header.get("Name0"));
        assertEquals("value1",header.get("name1"));
        assertEquals("value1",header.get("Name1"));
        assertEquals(null,header.get("Name2"));

        assertEquals("value0",header.getField("name0").getValue());
        assertEquals("value0",header.getField("Name0").getValue());
        assertEquals("value1",header.getField("name1").getValue());
        assertEquals("value1",header.getField("Name1").getValue());
        assertEquals(null,header.getField("Name2"));

        assertEquals("value0",header.getField(0).getValue());
        assertEquals("value1",header.getField(1).getValue());
        try
        {
            header.getField(2);
            Assert.fail();
        }
        catch(NoSuchElementException e)
        {}
    }
