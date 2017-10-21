    @Test
    public void testAddValues_List_Empty()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");

        // Add to the key
        List<String> extras = new ArrayList<String>();
        mm.addValues(key,extras);

        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");
    }
