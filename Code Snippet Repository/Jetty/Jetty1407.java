    @Test
    public void testAddQuotedCSV() throws Exception
    {
        HttpFields fields = new HttpFields();

        fields.put("some", "value");
        fields.add("name", "\"zero\"");
        fields.add("name", "one, \"1 + 1\"");
        fields.put("other", "value");
        fields.add("name", "three");
        fields.add("name", "four, I V");

        List<String> list = fields.getCSV("name",false);
        assertEquals("zero",HttpFields.valueParameters(list.get(0),null));
        assertEquals("one",HttpFields.valueParameters(list.get(1),null));
        assertEquals("1 + 1",HttpFields.valueParameters(list.get(2),null));
        assertEquals("three",HttpFields.valueParameters(list.get(3),null));
        assertEquals("four",HttpFields.valueParameters(list.get(4),null));
        assertEquals("I V",HttpFields.valueParameters(list.get(5),null));
        
        fields.addCSV("name","six");
        list = fields.getCSV("name",false);
        assertEquals("zero",HttpFields.valueParameters(list.get(0),null));
        assertEquals("one",HttpFields.valueParameters(list.get(1),null));
        assertEquals("1 + 1",HttpFields.valueParameters(list.get(2),null));
        assertEquals("three",HttpFields.valueParameters(list.get(3),null));
        assertEquals("four",HttpFields.valueParameters(list.get(4),null));
        assertEquals("I V",HttpFields.valueParameters(list.get(5),null));
        assertEquals("six",HttpFields.valueParameters(list.get(6),null));
        
        fields.addCSV("name","1 + 1","7","zero");
        list = fields.getCSV("name",false);
        assertEquals("zero",HttpFields.valueParameters(list.get(0),null));
        assertEquals("one",HttpFields.valueParameters(list.get(1),null));
        assertEquals("1 + 1",HttpFields.valueParameters(list.get(2),null));
        assertEquals("three",HttpFields.valueParameters(list.get(3),null));
        assertEquals("four",HttpFields.valueParameters(list.get(4),null));
        assertEquals("I V",HttpFields.valueParameters(list.get(5),null));
        assertEquals("six",HttpFields.valueParameters(list.get(6),null));
        assertEquals("7",HttpFields.valueParameters(list.get(7),null));   
    }
