    public void stop(String command, int port, String key, boolean check) throws Exception
    {
        // System.out.printf("Attempting to send " + command + " to localhost:%d (%b)%n", port, check);
        try (Socket s = new Socket(InetAddress.getByName("127.0.0.1"), port))
        {
            // send stop command
            try (OutputStream out = s.getOutputStream())
            {
                out.write((key + "\r\n" + command + "\r\n").getBytes());
                out.flush();

                if (check)
                {
                    // check for stop confirmation
                    LineNumberReader lin = new LineNumberReader(new InputStreamReader(s.getInputStream()));
                    String response;
                    if ((response = lin.readLine()) != null)
                        assertEquals("Stopped", response);
                    else
                        throw new IllegalStateException("No stop confirmation");
                }
            }
        }
    }
