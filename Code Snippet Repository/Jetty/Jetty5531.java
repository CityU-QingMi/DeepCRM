    @Test
    public void testPutValues_VarArgs()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        mm.putValues(key,"gzip", "jar", "pack200");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");
    }
