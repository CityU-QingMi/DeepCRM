    @Test
    public void test14_23() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(HttpParser.class))
        {
            int offset=0;
            String response = connector.getResponse("GET /R1 HTTP/1.0\n" + "Connection: close\n" + "\n");
            offset=checkContains(response,offset,"HTTP/1.1 200","200")+1;

            offset=0;
            response=connector.getResponse("GET /R1 HTTP/1.1\n"+"Connection: close\n"+"\n");
            offset=checkContains(response,offset,"HTTP/1.1 400","400")+1;

            offset=0;
            response=connector.getResponse("GET /R1 HTTP/1.1\n"+"Host: localhost\n"+"Connection: close\n"+"\n");
            offset=checkContains(response,offset,"HTTP/1.1 200","200")+1;

            offset=0;
            response=connector.getResponse("GET /R1 HTTP/1.1\n"+"Host:\n"+"Connection: close\n"+"\n");
            offset=checkContains(response,offset,"HTTP/1.1 200","200")+1;
        }
    }
