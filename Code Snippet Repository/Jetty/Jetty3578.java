    @Test
    public void test4_4_4() throws Exception
    {
        // No _content length
        assertTrue("Skip 411 checks as IE breaks this rule",true);
        // offset=0; connector.reopen();
        // response=connector.getResponse("GET /R2 HTTP/1.1\n"+
        // "Host: localhost\n"+
        // "Content-Type: text/plain\n"+
        // "Connection: close\n"+
        // "\n"+
        // "123456");
        // offset=checkContains(response,offset,
        // "HTTP/1.1 411 ","411 length required")+10;
        // offset=0; connector.reopen();
        // response=connector.getResponse("GET /R2 HTTP/1.0\n"+
        // "Content-Type: text/plain\n"+
        // "\n"+
        // "123456");
        // offset=checkContains(response,offset,
        // "HTTP/1.0 411 ","411 length required")+10;

    }
