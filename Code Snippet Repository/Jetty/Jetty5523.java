    @Test
    public void testContainsSimpleValue()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        Assert.assertTrue("Contains Value [red]", mm.containsSimpleValue("red"));
        Assert.assertFalse("Contains Value [nutrition]", mm.containsValue("nutrition"));
    }
