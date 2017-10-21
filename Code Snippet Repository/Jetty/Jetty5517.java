    @Test
    public void testPutAll_MultiMapComplex()
    {
        MultiMap<String> mm = new MultiMap<>();

        assertMapSize(mm,0); // Shouldn't have anything yet.

        MultiMap<String> input = new MultiMap<>();
        input.putValues("food","apple","cherry","raspberry");
        input.put("color","red");
        input.putValues("amount","bushel","pint");

        mm.putAll(input);

        assertMapSize(mm,3);
        assertValues(mm,"food","apple","cherry","raspberry");
        assertValues(mm,"color","red");
        assertValues(mm,"amount","bushel","pint");
    }
