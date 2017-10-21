    @Test
    public void test10_4_18() throws Exception
    {
        // Expect Failure
        int offset=0;
        String response = connector.getResponse(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Expect: unknown\n" +
                        "Content-Type: text/plain\n" +
                        "Content-Length: 8\n" +
                        "\n");
        offset=checkContains(response,offset,"HTTP/1.1 417","8.2.3 expect failure")+1;
    }
