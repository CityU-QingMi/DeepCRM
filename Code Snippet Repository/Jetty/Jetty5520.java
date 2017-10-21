    @Test
    public void testToString()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.put("color","red");

        Assert.assertEquals("{color=red}", mm.toString());

        mm.putValues("food","apple","cherry","raspberry");

        Assert.assertEquals("{color=red, food=[apple, cherry, raspberry]}", mm.toString());
    }
