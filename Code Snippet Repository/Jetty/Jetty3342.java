    @Test
    public void testExcessiveHeader() throws Exception
    {
        int offset = 0;

        StringBuilder request = new StringBuilder();
        request.append("GET / HTTP/1.1\r\n");
        request.append("Host: localhost\r\n");
        request.append("Cookie: thisisastring\r\n");
        for(int i=0; i<1000; i++) {
            request.append(String.format("X-Header-%04d: %08x\r\n", i, i));
        }
        request.append("\r\n");

        String response = connector.getResponse(request.toString());
        offset = checkContains(response, offset, "HTTP/1.1 431");
        checkContains(response, offset, "<h1>Bad Message 431</h1>");
    }
