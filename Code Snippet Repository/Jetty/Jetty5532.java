    @Test
    public void testAdd()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        // Setup the key
        mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");

        // Add to the key
        mm.add(key,"jar");
        mm.add(key,"pack200");

        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");
    }
