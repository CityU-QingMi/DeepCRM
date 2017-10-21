    @Test
    public void testContainsValue_LazyList()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        Object list = LazyList.add(null, "bushel");
        list = LazyList.add(list, "pint");

        Assert.assertTrue("Contains Value [" + list + "]", mm.containsValue(list));
    }
