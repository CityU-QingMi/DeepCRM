    @Test
    public void testBigger() throws Exception
    {
        try (Socket socket = new Socket("localhost",_connector.getLocalPort());)
        {
            socket.getOutputStream().write("GET /resource/bigger.txt HTTP/1.0\n\n".getBytes());
            Thread.sleep(1000);
            String response = IO.toString(socket.getInputStream());
            Assert.assertThat(response,Matchers.startsWith("HTTP/1.1 200 OK"));
            Assert.assertThat(response,Matchers.containsString("   400\tThis is a big file" + LN + "     1\tThis is a big file"));
            Assert.assertThat(response,Matchers.endsWith("   400\tThis is a big file" + LN));
        }
    }
