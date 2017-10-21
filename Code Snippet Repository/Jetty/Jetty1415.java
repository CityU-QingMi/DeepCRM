    @Test
    public void testGetKnown() throws Exception
    {
        HttpFields header = new HttpFields();

        header.put("Connection", "value0");
        header.put(HttpHeader.ACCEPT, "value1");

        assertEquals("value0",header.get(HttpHeader.CONNECTION));
        assertEquals("value1",header.get(HttpHeader.ACCEPT));

        assertEquals("value0",header.getField(HttpHeader.CONNECTION).getValue());
        assertEquals("value1",header.getField(HttpHeader.ACCEPT).getValue());

        assertEquals(null,header.getField(HttpHeader.AGE));
        assertEquals(null,header.get(HttpHeader.AGE));
    }
