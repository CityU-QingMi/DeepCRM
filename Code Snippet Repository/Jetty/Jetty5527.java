    @Test
    public void testPut_Null_List()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";
        List<String> vals = null;

        mm.put(key,vals);
        assertMapSize(mm,1);
        assertNullValues(mm,key);
    }
