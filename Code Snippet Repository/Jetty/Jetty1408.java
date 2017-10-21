    @Test
    public void testGetQualityCSV() throws Exception
    {
        HttpFields fields = new HttpFields();

        fields.put("some", "value");
        fields.add("name", "zero;q=0.9,four;q=0.1");
        fields.put("other", "value");
        fields.add("name", "nothing;q=0");
        fields.add("name", "one;q=0.4");
        fields.add("name", "three;x=y;q=0.2;a=b,two;q=0.3");
        fields.add("name", "first;");

        
        List<String> list = fields.getQualityCSV("name");
        assertEquals("first",HttpFields.valueParameters(list.get(0),null));
        assertEquals("zero",HttpFields.valueParameters(list.get(1),null));
        assertEquals("one",HttpFields.valueParameters(list.get(2),null));
        assertEquals("two",HttpFields.valueParameters(list.get(3),null));
        assertEquals("three",HttpFields.valueParameters(list.get(4),null));
        assertEquals("four",HttpFields.valueParameters(list.get(5),null));
    }
