    public HttpTester.Response request(CharSequence rawRequest) throws IOException
    {
        Socket sock = open();
        try
        {
            send(sock,rawRequest);
            return read(sock);
        }
        finally
        {
            close(sock);
        }
    }
