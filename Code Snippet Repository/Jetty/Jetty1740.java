    @Test
    public void testReset() throws Exception
    {
        try (ServerSocket connector = new ServerSocket(0);
            Socket client = new Socket("127.0.0.1", connector.getLocalPort());
            Socket server = connector.accept();)
        {
            client.setTcpNoDelay(true);
            client.setSoLinger(true, 0);
            server.setTcpNoDelay(true);
            server.setSoLinger(true, 0);

            client.getOutputStream().write(1);
            assertEquals(1, server.getInputStream().read());
            server.getOutputStream().write(1);
            assertEquals(1, client.getInputStream().read());

            // Server generator shutdowns output after non persistent sending response.
            server.shutdownOutput();

            // client endpoint reads EOF and shutdown input as result
            assertEquals(-1, client.getInputStream().read());
            client.shutdownInput();

            // client connection see's EOF and shutsdown output as no more requests to be sent.
            client.shutdownOutput();

            // Since input already shutdown, client also closes socket.
            client.close();

            // Server reads the EOF from client oshut and shut's down it's input
            assertEquals(-1, server.getInputStream().read());
            server.shutdownInput();

            // Since output was already shutdown, server closes
            server.close();
        }
    }
