    @Test
    public void test5_2_1() throws Exception
    {
        // Default Host
        int offset=0;
        String response = connector.getResponse("GET http://VirtualHost:8888/path/R1 HTTP/1.1\n" + "Host: wronghost\n" + "Connection: close\n" + "\n");
        offset=checkContains(response,offset,"HTTP/1.1 200","Virtual host")+1;
        offset=checkContains(response,offset,"Virtual Dump","Virtual host")+1;
        offset=checkContains(response,offset,"pathInfo=/path/R1","Virtual host")+1;
        offset=checkContains(response,offset,"servername=VirtualHost","Virtual host")+1;
    }
