    private void testList(List<String> list1) throws Exception
    {
        JSON json = new JSON();
        json.addConvertor(List.class, new JSONCollectionConvertor());

        Map<String, Object> object1 = new HashMap<String, Object>();
        String field = "field";
        object1.put(field, list1);

        String string = json.toJSON(object1);
        Assert.assertTrue(string.contains(list1.getClass().getName()));

        @SuppressWarnings("unchecked")
        Map<String, Object> object2 = (Map<String, Object>)json.parse(new JSON.StringSource(string));
        @SuppressWarnings("unchecked")
        List<String> list2 = (List<String>)object2.get(field);

        Assert.assertSame(list1.getClass(), list2.getClass());
        Assert.assertEquals(list1, list2);
    }
