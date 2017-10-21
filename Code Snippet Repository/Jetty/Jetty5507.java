    @Test
    public void testPut()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";

        mm.put(key,"gzip");
        assertMapSize(mm,1);
        assertValues(mm,key,"gzip");
    }
