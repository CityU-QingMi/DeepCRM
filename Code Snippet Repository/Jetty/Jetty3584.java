    @Test
    public void test8_2_3_dash5() throws Exception
    {
        // Expect with body: client sends the content right away, we should not send 100-Continue
        int offset=0;
        String response = connector.getResponse(
                "GET /R1 HTTP/1.1\n" +
                        "Host: localhost\n" +
                        "Expect: 100-continue\n" +
                        "Content-Type: text/plain\n" +
                        "Content-Length: 8\n" +
                        "Connection: close\n" +
                        "\n" +
                        "123456\015\012");
        checkNotContained(response, offset, "HTTP/1.1 100 ", "8.2.3 expect 100");
        offset=checkContains(response,offset,"HTTP/1.1 200 OK","8.2.3 expect with body")+1;
    }
