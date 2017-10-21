    @Test
    public void testClear()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        assertMapSize(mm,3);

        mm.clear();

        assertMapSize(mm,0);
    }
