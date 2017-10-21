    @Test
    public void testNextTokenOnContentDisposition()
    {
        String content_disposition = "form-data; name=\"fileup\"; filename=\"Taken on Aug 22 \\ 2012.jpg\"";
     
        QuotedStringTokenizer tok=new QuotedStringTokenizer(content_disposition,";",false,true);
        
        assertEquals("form-data", tok.nextToken().trim());
        assertEquals("name=\"fileup\"", tok.nextToken().trim());
        assertEquals("filename=\"Taken on Aug 22 \\ 2012.jpg\"", tok.nextToken().trim());
    }
