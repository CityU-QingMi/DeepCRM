    @Test
    public void testIncompleteSequestInValue() throws Exception
    {
        byte[] bytes= { 101, 102, 61, 103, -50, 38, 97, 98, 61, 99, 100 };
        String test=new String(bytes,StandardCharsets.UTF_8);
        String name = "ef";
        String value = "g"+Utf8Appendable.REPLACEMENT;

        fromString(test,test,name,value,false);
        fromInputStream(test,bytes,name,value,false);
    }
