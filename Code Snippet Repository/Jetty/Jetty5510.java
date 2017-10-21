    @Test
    public void testAddValues_StringArray_Empty()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");

        // Add to the key
        String extras[] = new String[0];
        mm.addValues(key,extras);

        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");
    }
