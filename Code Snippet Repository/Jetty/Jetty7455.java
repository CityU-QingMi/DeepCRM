    @Test
    public void test3_9()
    {
        HttpFields fields = new HttpFields();

        fields.put("Q","bbb;q=0.5,aaa,ccc;q=0.002,d;q=0,e;q=0.0001,ddd;q=0.001,aa2,abb;q=0.7");
        Enumeration<String> qualities = fields.getValues("Q",", \t");
        List<?> list = HttpFields.qualityList(qualities);
        Assert.assertEquals("Quality parameters","aaa",HttpFields.valueParameters(list.get(0).toString(),null));
        Assert.assertEquals("Quality parameters","aa2",HttpFields.valueParameters(list.get(1).toString(),null));
        Assert.assertEquals("Quality parameters","abb",HttpFields.valueParameters(list.get(2).toString(),null));
        Assert.assertEquals("Quality parameters","bbb",HttpFields.valueParameters(list.get(3).toString(),null));
        Assert.assertEquals("Quality parameters","ccc",HttpFields.valueParameters(list.get(4).toString(),null));
        Assert.assertEquals("Quality parameters","ddd",HttpFields.valueParameters(list.get(5).toString(),null));
    }
