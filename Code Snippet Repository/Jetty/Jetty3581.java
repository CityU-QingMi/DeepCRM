    @Test
    public void test5_2() throws Exception
    {
        // Virtual Host
        int offset=0;
        String response = connector.getResponse("GET /path/R1 HTTP/1.1\n" + "Host: VirtualHost\n" + "Connection: close\n" + "\n");
        offset=checkContains(response,offset,"HTTP/1.1 200","2. virtual host field")+1;
        offset=checkContains(response,offset,"Virtual Dump","2. virtual host field")+1;
        offset=checkContains(response,offset,"pathInfo=/path/R1","2. virtual host field")+1;

        // Virtual Host case insensitive
        offset=0;
        response=connector.getResponse("GET /path/R1 HTTP/1.1\n"+"Host: ViRtUalhOst\n"+"Connection: close\n"+"\n");
        offset=checkContains(response,offset,"HTTP/1.1 200","2. virtual host field")+1;
        offset=checkContains(response,offset,"Virtual Dump","2. virtual host field")+1;
        offset=checkContains(response,offset,"pathInfo=/path/R1","2. virtual host field")+1;

        // Virtual Host
        offset=0;
        response=connector.getResponse("GET /path/R1 HTTP/1.1\n"+"\n");
        offset=checkContains(response,offset,"HTTP/1.1 400","3. no host")+1;
    }
