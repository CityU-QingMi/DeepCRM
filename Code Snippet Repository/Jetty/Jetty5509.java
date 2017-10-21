    @Test
    public void testAddValues_StringArray()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");

        // Add to the key
        String extras[] = { "jar", "pack200", "zip" };
        mm.addValues(key,extras);

        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200","zip");
    }
