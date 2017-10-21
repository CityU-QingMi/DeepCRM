    @Test
    public void testBadHandshake() throws Exception
    {
        try (Socket socket = new Socket("127.0.0.1", _port); OutputStream out = socket.getOutputStream())
        {
            out.write("Rubbish".getBytes());
            out.flush();

            socket.setSoTimeout(1000);
            // Expect TLS message type == 21: Alert
            Assert.assertThat(socket.getInputStream().read(), Matchers.equalTo(21));
        }
    }
