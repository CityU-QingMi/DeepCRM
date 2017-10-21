    @Test
    public void test9_2() throws Exception
    {
         int offset=0;

         String response=connector.getResponse("OPTIONS * HTTP/1.1\n"+
             "Connection: close\n"+
             "Host: localhost\n"+
             "\n");
         offset=checkContains(response,offset, "HTTP/1.1 200","200")+1;

         offset=0;
         response=connector.getResponse("GET * HTTP/1.1\n"+
             "Connection: close\n"+
             "Host: localhost\n"+
             "\n");
         offset=checkContains(response,offset, "HTTP/1.1 400","400")+1;
    }
