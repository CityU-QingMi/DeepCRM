    @Test
    public void testRemoveValue()
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

    }
