    @Test
    public void testPutValues_List()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        List<String> input = new ArrayList<String>();
        input.add("gzip");
        input.add("jar");
        input.add("pack200");

        mm.putValues(key,input);
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip","jar","pack200");
    }
