    @Test
    public void testContainsValue()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        List<String> acr = new ArrayList<>();
        acr.add("apple");
        acr.add("cherry");
        acr.add("raspberry");
        Assert.assertTrue("Contains Value [apple,cherry,raspberry]", mm.containsValue(acr));
        Assert.assertFalse("Contains Value [nutrition]", mm.containsValue("nutrition"));
    }
