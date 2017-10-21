    @Test
    public void test3_9() throws Exception
    {
        HttpFields fields=new HttpFields();

        fields.put("Q","bbb;q=0.5,aaa,ccc;q=0.002,d;q=0,e;q=0.0001,ddd;q=0.001,aa2,abb;q=0.7");
        Enumeration<String> qualities=fields.getValues("Q",", \t");
        List<String> list=HttpFields.qualityList(qualities);
        assertEquals("Quality parameters","aaa",HttpFields.valueParameters(list.get(0),null));
        assertEquals("Quality parameters","aa2",HttpFields.valueParameters(list.get(1),null));
        assertEquals("Quality parameters","abb",HttpFields.valueParameters(list.get(2),null));
        assertEquals("Quality parameters","bbb",HttpFields.valueParameters(list.get(3),null));
        assertEquals("Quality parameters","ccc",HttpFields.valueParameters(list.get(4),null));
        assertEquals("Quality parameters","ddd",HttpFields.valueParameters(list.get(5),null));
    }
