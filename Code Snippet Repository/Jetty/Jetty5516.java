    @Test
    public void testPutAll_MultiMap_Simple()
    {
        MultiMap<String> mm = new MultiMap<>();

        assertMapSize(mm,0); // Shouldn't have anything yet.

        MultiMap<String> input = new MultiMap<>();
        input.put("food","apple");
        input.put("color","red");
        input.put("amount","bushel");

        mm.putAll(input);

        assertMapSize(mm,3);
        assertValues(mm,"food","apple");
        assertValues(mm,"color","red");
        assertValues(mm,"amount","bushel");
    }
