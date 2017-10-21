    @Test
    public void test5_2_2() throws Exception
    {
        // Default Host
        int offset=0;
        String response = connector.getResponse("GET /path/R1 HTTP/1.1\n" + "Host: localhost\n" + "Connection: close\n" + "\n");
        offset=checkContains(response,offset,"HTTP/1.1 200","Default host")+1;
        offset=checkContains(response,offset,"Dump HttpHandler","Default host")+1;
        offset=checkContains(response,offset,"pathInfo=/path/R1","Default host")+1;

        // Virtual Host
        offset=0;
        response=connector.getResponse("GET /path/R2 HTTP/1.1\n"+"Host: VirtualHost\n"+"Connection: close\n"+"\n");
        offset=checkContains(response,offset,"HTTP/1.1 200","Default host")+1;
        offset=checkContains(response,offset,"Virtual Dump","virtual host")+1;
        offset=checkContains(response,offset,"pathInfo=/path/R2","Default host")+1;
    }
