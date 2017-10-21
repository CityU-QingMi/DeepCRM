    @Test
    public void testToStringArrayMap()
    {
        MultiMap<String> mm = new MultiMap<>();
        mm.putValues("food","apple","cherry","raspberry");
        mm.put("color","red");
        mm.putValues("amount","bushel","pint");

        assertMapSize(mm,3);

        Map<String,String[]> sam = mm.toStringArrayMap();
        Assert.assertEquals("String Array Map.size",3,sam.size());

        assertArray("toStringArrayMap(food)", sam.get("food"), "apple","cherry","raspberry");
        assertArray("toStringArrayMap(color)", sam.get("color"), "red");
        assertArray("toStringArrayMap(amount)", sam.get("amount"), "bushel","pint");
    }
