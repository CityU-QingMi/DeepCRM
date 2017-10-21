    private void testSet(Set<String> set1)
    {
        JSON json = new JSON();
        json.addConvertor(Set.class, new JSONCollectionConvertor());

        String string = json.toJSON(set1);
        Assert.assertTrue(string.contains(set1.getClass().getName()));

        @SuppressWarnings("unchecked")
        Set<String> set2 = (Set<String>)json.parse(new JSON.StringSource(string));

        Assert.assertSame(set1.getClass(), set2.getClass());
        Assert.assertEquals(set1, set2);
    }
