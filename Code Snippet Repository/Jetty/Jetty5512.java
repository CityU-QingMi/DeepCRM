    @Test
    public void testRemoveValue_InvalidItem()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.putValues(key,"gzip","jar","pack200");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");

        // Remove a value that isn't there
        mm.removeValue(key,"msi");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");
    }
