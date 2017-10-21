    @Test
    public void testIncompleteSequestAtTheEnd2() throws Exception
    {
        byte[] bytes={ 97, 98, 61, -50 };
        String test=new String(bytes,StandardCharsets.UTF_8);
        String expected = ""+Utf8Appendable.REPLACEMENT;

        fromString(test,test,"ab",expected,false);
        fromInputStream(test,bytes,"ab",expected,false);
        
    }
