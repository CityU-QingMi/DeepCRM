    @Test
    public void testPutValues_StringArray()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        String input[] = { "gzip", "jar", "pack200" };
        mm.putValues(key,input);
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");
    }
