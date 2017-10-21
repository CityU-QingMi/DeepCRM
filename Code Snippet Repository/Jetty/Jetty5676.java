    @Test
    public void testIncompleteSequestAtTheEnd() throws Exception
    {
        byte[] bytes= { 97, 98, 61, 99, -50 };
        String test=new String(bytes,StandardCharsets.UTF_8);
        String expected = "c"+Utf8Appendable.REPLACEMENT;

        fromString(test,test,"ab",expected,false);
        fromInputStream(test,bytes,"ab",expected,false);
    }
