   public List<HttpTester.Response> requests(CharSequence rawRequests) throws IOException
    {
        Socket sock = open();
        try
        {
            send(sock,rawRequests);

            // Collect response
            String rawResponses = IO.toString(sock.getInputStream());
            DEBUG("--raw-response--\n" + rawResponses);
            return readResponses(rawResponses);
        }
        finally
        {
            close(sock);
        }
    }
