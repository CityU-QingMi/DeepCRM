    @Test
    public void testPut_Null_String()
    {
        MultiMap<String> mm = new MultiMap<>();

        String key = "formats";
        String val = null;

        mm.put(key,val);
        assertMapSize(mm,1);
        assertNullValues(mm,key);
    }
