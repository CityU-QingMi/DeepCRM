    @Test
    public void test8_1() throws Exception
    {
        int offset=0;
        String response = connector.getResponse("GET /R1 HTTP/1.1\n" + "Host: localhost\n" + "\n", 250, TimeUnit.MILLISECONDS);
        offset=checkContains(response,offset,"HTTP/1.1 200 OK\015\012","8.1.2 default")+10;
        checkContains(response,offset,"Content-Length: ","8.1.2 default");

        LocalEndPoint endp=connector.executeRequest("GET /R1 HTTP/1.1\n"+"Host: localhost\n"+"\n"+
            "GET /R2 HTTP/1.1\n"+"Host: localhost\n"+"Connection: close\n"+"\n"+
            "GET /R3 HTTP/1.1\n"+"Host: localhost\n"+"Connection: close\n"+"\n");

        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK\015\012","8.1.2 default")+1;
        offset=checkContains(response,offset,"/R1","8.1.2 default")+1;

        offset=0;
        response = endp.getResponse();
        offset=checkContains(response,offset,"HTTP/1.1 200 OK\015\012","8.1.2.2 pipeline")+11;
        offset=checkContains(response,offset,"Connection: close","8.1.2.2 pipeline")+1;
        offset=checkContains(response,offset,"/R2","8.1.2.1 close")+3;

        offset=0;
        response = endp.getResponse();
        assertThat(response,nullValue());

    }
