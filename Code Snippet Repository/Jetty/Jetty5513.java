    @Test
    public void testRemoveValue_AllItems()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.putValues(key,"gzip","jar","pack200");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");

        // Remove a value
        mm.removeValue(key,"jar");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","pack200");

        // Remove another value
        mm.removeValue(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"pack200");

        // Remove last value
        mm.removeValue(key,"pack200");
        assertMapSize(mm,0);  // should be empty now
    }
