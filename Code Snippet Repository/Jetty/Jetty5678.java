    @Test
    public void testIncompleteSequestInName() throws Exception
    {
        byte[] bytes= { 101, -50, 61, 102, 103, 38, 97, 98, 61, 99, 100 };
        String test=new String(bytes,StandardCharsets.UTF_8);
        String name = "e"+Utf8Appendable.REPLACEMENT;
        String value = "fg";

        fromString(test,test,name,value,false);
        fromInputStream(test,bytes,name,value,false);
    }
