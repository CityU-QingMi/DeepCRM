    @Test
    public void test8_2_4() throws Exception
    {
        // Expect 100 not sent
        int offset=0;
        String response = connector.getResponse("GET /R1?error=401 HTTP/1.1\n" +
                "Host: localhost\n" +
                "Expect: 100-continue\n" +
                "Content-Type: text/plain\n" +
                "Content-Length: 8\n" +
                "\n");
        checkNotContained(response,offset,"HTTP/1.1 100","8.2.3 expect 100");
        offset=checkContains(response,offset,"HTTP/1.1 401 ","8.2.3 expect 100")+1;
        offset=checkContains(response,offset,"Connection: close","8.2.3 expect 100")+1;
    }
