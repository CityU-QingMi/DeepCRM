    private void assumeConnectTimeout(String host, int port, int connectTimeout) throws IOException
    {
        try (Socket socket = new Socket())
        {
            // Try to connect to a private address in the 10.x.y.z range.
            // These addresses are usually not routed, so an attempt to
            // connect to them will hang the connection attempt, which is
            // what we want to simulate in this test.
            socket.connect(new InetSocketAddress(host, port), connectTimeout);
            // Abort the test if we can connect.
            Assume.assumeTrue(false);
        }
        catch (SocketTimeoutException x)
        {
            // Expected timeout during connect, continue the test.
            Assume.assumeTrue(true);
        }
        catch (Throwable x)
        {
            // Abort if any other exception happens.
            Assume.assumeTrue(false);
        }
    }
