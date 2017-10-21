    @Test
    public void testPutAll_Map()
    {
        MultiMap<String> mm = new MultiMap<>();

        assertMapSize(mm,0); // Shouldn't have anything yet.

        Map<String,String> input = new HashMap<String,String>();
        input.put("food","apple");
        input.put("color","red");
        input.put("amount","bushel");

        mm.putAllValues(input);

        assertMapSize(mm,3);
        assertValues(mm,"food","apple");
        assertValues(mm,"color","red");
        assertValues(mm,"amount","bushel");
    }
