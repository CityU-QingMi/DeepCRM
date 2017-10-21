    @Test
    public void testAsyncRead() throws Exception
    {
        String header="GET /ctx/path/info?start=2000&dispatch=1500 HTTP/1.1\r\n"+
            "Host: localhost\r\n"+
            "Content-Length: 10\r\n"+
            "Connection: close\r\n"+
            "\r\n";
        String body="12345678\r\n";

        try (Socket socket = new Socket("localhost",_port))
        {
            socket.setSoTimeout(10000);
            socket.getOutputStream().write(header.getBytes(StandardCharsets.ISO_8859_1));
            socket.getOutputStream().write(body.getBytes(StandardCharsets.ISO_8859_1),0,2);
            Thread.sleep(500);
            socket.getOutputStream().write(body.getBytes(StandardCharsets.ISO_8859_1),2,8);

            String response = IO.toString(socket.getInputStream());
            __latch.await(1,TimeUnit.SECONDS);
            assertThat(response,startsWith("HTTP/1.1 200 OK"));
            assertThat(__history,contains(
                "REQUEST /ctx/path/info",
                "initial",
                "start",
                "async-read=10",
                "dispatch",
                "ASYNC /ctx/path/info",
                "!initial",
                "onComplete"));
        }
    }
