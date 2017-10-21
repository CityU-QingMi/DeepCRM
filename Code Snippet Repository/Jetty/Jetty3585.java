    @Test
    public void test8_2_3() throws Exception
    {
        int offset=0;
        // Expect 100
        LocalConnector.LocalEndPoint endp =connector.executeRequest("GET /R1 HTTP/1.1\n"+
                "Host: localhost\n"+
                "Connection: close\n"+
                "Expect: 100-continue\n"+
                "Content-Type: text/plain\n"+
                "Content-Length: 8\n"+
                "\n");
        String infomational= endp.getResponse();
        offset=checkContains(infomational,offset,"HTTP/1.1 100 ","8.2.3 expect 100")+1;
        checkNotContained(infomational,offset,"HTTP/1.1 200","8.2.3 expect 100");

        endp.addInput("654321\015\012");

        String response= endp.getResponse();
        offset=0;
        offset=checkContains(response,offset,"HTTP/1.1 200","8.2.3 expect 100")+1;
        offset=checkContains(response,offset,"654321","8.2.3 expect 100")+1;
    }
