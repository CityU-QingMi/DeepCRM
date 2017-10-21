    @Test
    public void testStatus() throws Exception
    {
        ShutdownMonitor monitor = ShutdownMonitor.getInstance();
        // monitor.setDebug(true);
        monitor.setPort(0);
        monitor.setExitVm(false);
        monitor.start();
        String key = monitor.getKey();
        int port = monitor.getPort();

        // Try more than once to be sure that the ServerSocket has not been closed.
        for (int i = 0; i < 2; ++i)
        {
            try (Socket socket = new Socket("localhost", port))
            {
                OutputStream output = socket.getOutputStream();
                String command = "status";
                output.write((key + "\r\n" + command + "\r\n").getBytes());
                output.flush();

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String reply = input.readLine();
                assertEquals("OK", reply);
                // Socket must be closed afterwards.
                Assert.assertNull(input.readLine());
            }
        }
    }
