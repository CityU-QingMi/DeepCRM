    @Test
    public void testContainsKey()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        Assert.assertTrue("Contains Key [color]", mm.containsKey("color"));
        Assert.assertFalse("Contains Key [nutrition]", mm.containsKey("nutrition"));
    }
