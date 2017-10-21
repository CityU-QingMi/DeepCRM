    private void assumeConnectTimeout(String host, int port, int connectTimeout) throws IOException
    {
        boolean socketTimeout = false;
        
        try (Socket socket = new Socket())
        {
            // Try to connect to a private address in the 10.x.y.z range.
            // These addresses are usually not routed, so an attempt to
            // connect to them will hang the connection attempt, which is
            // what we want to simulate in this test.
            socket.connect(new InetSocketAddress(host, port), connectTimeout);
        }
        catch (SocketTimeoutException x)
        {
            // We expect a timeout during connect, allow test to continue.
            socketTimeout = true;
        }
        catch (Throwable x)
        {
            // Dump stacktrace when there is an unexpected test failure
            // Useful when debugging
            x.printStackTrace(System.err);
        }
        
        // Abort the test if we can connect.
        Assume.assumeTrue("Should have seen connect timeout",socketTimeout);
    }
